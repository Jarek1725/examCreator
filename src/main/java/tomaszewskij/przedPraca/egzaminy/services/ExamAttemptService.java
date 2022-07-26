package tomaszewskij.przedPraca.egzaminy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tomaszewskij.przedPraca.egzaminy.DTO.ExamAttemptFilter;
import tomaszewskij.przedPraca.egzaminy.models.AttemptQuestion;
import tomaszewskij.przedPraca.egzaminy.models.Exam;
import tomaszewskij.przedPraca.egzaminy.models.ExamAttempts;
import tomaszewskij.przedPraca.egzaminy.models.Question;
import tomaszewskij.przedPraca.egzaminy.repositories.ExamAttemptRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ExamAttemptService {
    private final ExamAttemptRepository examAttemptRepository;
    private final ExamService examService;
    private final QuestionService questionService;
    private final AppUserService appUserService;


    @Autowired
    public ExamAttemptService(ExamAttemptRepository examAttemptRepository, ExamService examService, QuestionService questionService, AppUserService appUserService) {
        this.examAttemptRepository = examAttemptRepository;
        this.examService = examService;
        this.questionService = questionService;
        this.appUserService = appUserService;
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
}
