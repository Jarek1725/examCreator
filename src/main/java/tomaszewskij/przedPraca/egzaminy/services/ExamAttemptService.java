package tomaszewskij.przedPraca.egzaminy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tomaszewskij.przedPraca.egzaminy.DTO.ExamAttemptFilter;
import tomaszewskij.przedPraca.egzaminy.DTO.SelectedAnswers;
import tomaszewskij.przedPraca.egzaminy.exceptions.NotFoundException;
import tomaszewskij.przedPraca.egzaminy.models.*;
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


    @Autowired
    public ExamAttemptService(ExamAttemptRepository examAttemptRepository, ExamService examService, QuestionService questionService, AppUserService appUserService, AnswerService answerService) {
        this.examAttemptRepository = examAttemptRepository;
        this.examService = examService;
        this.questionService = questionService;
        this.appUserService = appUserService;
        this.answerService = answerService;
    }

    public List<ExamAttempts> getExams(ExamAttemptFilter filter) {
        return examAttemptRepository.findAllFilterAndGroup(filter.getExamId(), filter.getPublicToken());
    }

    public List<Question> startAttempt(String examPublicId, String appUserPrivateToken) {
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

        return questions;
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
                ()->new NotFoundException("Not found attempt with id:"+attemptId, "Attempt"));

        examAttempts.getAttemptQuestion().forEach(e->{
            System.out.println("ODPOWIEDZI");
            e.getQuestion().getAnswers().forEach(System.out::println);
        });

        for (SelectedAnswers selectedAnswer : selectedAnswers) {
            Question question = questionService.findById(selectedAnswer.getQuestionId());

            if (checkIfCorrectAnswers(question, selectedAnswer.getSelectedAnswersId())) {
                points += question.getPoints();
            }
        }
    }

    public boolean checkIfCorrectAnswers(Question question, List<Long> answersId) {
        List<Answer> userAnswers = new ArrayList<>();
        List<Answer> correctAnswers = question.getAnswers().stream().filter(Answer::isCorrect).collect(Collectors.toList());
        answersId.forEach(answer -> {
            Answer answerById = answerService.findById(answer);
            userAnswers.add(answerById);
        });

        userAnswers.sort(Comparator.comparing(Answer::getId));

        correctAnswers.sort(Comparator.comparing(Answer::getId));

        return correctAnswers.equals(userAnswers);
    }
}
