package tomaszewskij.przedPraca.egzaminy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tomaszewskij.przedPraca.egzaminy.DTO.ExamAttemptFilter;
import tomaszewskij.przedPraca.egzaminy.DTO.SelectedAnswers;
import tomaszewskij.przedPraca.egzaminy.DTO.StartAttempt;
import tomaszewskij.przedPraca.egzaminy.exceptions.NotFoundException;
import tomaszewskij.przedPraca.egzaminy.models.*;
import tomaszewskij.przedPraca.egzaminy.repositories.AttemptAnswerRepository;
import tomaszewskij.przedPraca.egzaminy.repositories.ExamAttemptRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExamAttemptService {
    private final ExamAttemptRepository examAttemptRepository;
    private final ExamService examService;
    private final QuestionService questionService;
    private final AppUserService appUserService;
    private final AnswerService answerService;
    private final AttemptAnswerRepository attemptAnswerRepository;


    @Autowired
    public ExamAttemptService(ExamAttemptRepository examAttemptRepository, ExamService examService, QuestionService questionService, AppUserService appUserService, AnswerService answerService, AttemptAnswerRepository attemptAnswerRepository) {
        this.examAttemptRepository = examAttemptRepository;
        this.examService = examService;
        this.questionService = questionService;
        this.appUserService = appUserService;
        this.answerService = answerService;
        this.attemptAnswerRepository = attemptAnswerRepository;
    }

    public List<ExamAttempts> getExams(ExamAttemptFilter filter) {
        return examAttemptRepository.findAllFilterAndGroup(filter.getExamId(), filter.getPublicToken());
    }

    public StartAttempt startAttempt(String examPublicId, String appUserPrivateToken) {
        Exam exam = examService.getByPublicId(examPublicId);
        int howManyQuestionsShow = exam.getHowManyQuestionsShow();

        List<Question> questions = generateRandomQuestions(exam, howManyQuestionsShow);


        questions.forEach(question -> {
            Collections.shuffle(question.getAnswers());
        });

        Collections.shuffle(questions);

        ExamAttempts examAttempt = new ExamAttempts();

        List<AttemptQuestion> attemptQuestions = new ArrayList<>();
        questions.forEach(question -> {
            attemptQuestions.add(new AttemptQuestion(examAttempt, question));
        });

        examAttempt.setExam(exam);
        examAttempt.setUser(appUserService.getAppUserByPrivateToken(appUserPrivateToken));
        examAttempt.setAttemptQuestion(attemptQuestions);

        examAttemptRepository.save(examAttempt);

        StartAttempt startAttempt = new StartAttempt(questions, examAttempt.getId());

        return startAttempt;
    }

    public List<Question> generateRandomQuestions(Exam exam, int howManyQuestions) {
        List<Question> returnQuestions = null;

        if (howManyQuestions == 0) {
            returnQuestions = exam.getQuestions();
        } else {
            Random r = new Random();
            returnQuestions = r.ints(0, exam.getQuestions().size())
                    .mapToObj(rand -> exam.getQuestions().get(rand))
                    .filter(e -> !e.isHidden())
                    .distinct()
                    .limit(howManyQuestions)
                    .collect(Collectors.toList());
        }
        return returnQuestions;
    }

    public void endAttempt(List<SelectedAnswers> selectedAnswers, Long attemptId, String appUserPrivateToken) {
        Integer points = 0;
        ExamAttempts examAttempts = examAttemptRepository.findById(attemptId).orElseThrow(
                () -> new NotFoundException("Not found attempt with id:" + attemptId, "Attempt"));

        List<AttemptAnswer> attemptAnswers = new ArrayList<>();
        List<Answer> questionAnswersList = new ArrayList<>();

        for (AttemptQuestion attemptQuestion : examAttempts.getAttemptQuestion()) {
            SelectedAnswers selectedAnswer = selectedAnswers.stream()
                    .filter(e -> e.getQuestionId().equals(attemptQuestion.getQuestion().getId()))
                    .findFirst().orElse(null);
            if (selectedAnswer != null) {
                for (Long answer : selectedAnswer.getSelectedAnswersId()) {
                    Answer answerById = answerService.findById(answer);
                    attemptAnswers.add(new AttemptAnswer(attemptQuestion, answerById));
                    questionAnswersList.add(answerById);
                }
                if (checkIfCorrectAnswers(attemptQuestion.getQuestion(), questionAnswersList)) {
                    points += attemptQuestion.getQuestion().getPoints();
                }
                attemptQuestion.setAttemptAnswers(attemptAnswers);
                questionAnswersList = new ArrayList<>();
                attemptAnswers = new ArrayList<>();
            }
        }

        examAttempts.setScore(points);
        examAttemptRepository.save(examAttempts);

    }

    public boolean checkIfCorrectAnswers(Question question, List<Answer> userAnswers) {
        List<Answer> correctAnswers = question.getAnswers().stream().filter(Answer::isCorrect).collect(Collectors.toList());

        userAnswers.sort(Comparator.comparing(Answer::getId));

        correctAnswers.sort(Comparator.comparing(Answer::getId));

        return correctAnswers.equals(userAnswers);
    }

    public ExamAttempts attemptResult(Long attemptId) {
        ExamAttempts examAttempts = examAttemptRepository.findById(attemptId).orElseThrow(
                () -> new NotFoundException("Not found attempt with id: " + attemptId, "Exam attempt"));

        Exam exam = examService.getById(examAttempts.getExam().getId());

        ExamRating examRating = exam.getExamRatings().stream().filter(e -> e.getAppUser().getId().equals(examAttempts.getUser().getId())).findFirst().orElseGet(ExamRating::new);
        examAttempts.getExam().setExamRatings(List.of(examRating));

        return examAttempts;
    }
}
