package tomaszewskij.przedPraca.egzaminy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import tomaszewskij.przedPraca.egzaminy.DTO.ExamAttemptFilter;
import tomaszewskij.przedPraca.egzaminy.DTO.ExamAttemptResult;
import tomaszewskij.przedPraca.egzaminy.DTO.SelectedAnswers;
import tomaszewskij.przedPraca.egzaminy.models.ExamAttempts;
import tomaszewskij.przedPraca.egzaminy.models.Question;
import tomaszewskij.przedPraca.egzaminy.services.ExamAttemptService;

import java.util.List;

@Controller
public class ExamAttemptController {

    private final ExamAttemptService examAttemptService;

    @Autowired
    public ExamAttemptController(ExamAttemptService examAttemptService) {
        this.examAttemptService = examAttemptService;
    }

    @QueryMapping
    public List<ExamAttempts> examAttempts(@Argument ExamAttemptFilter filter){
        return examAttemptService.getExams(filter);
    }

    @MutationMapping
    public List<Question> startAttempt(@Argument String examPublicId, @Argument String appUserPrivateToken){
        List<Question> questions = examAttemptService.startAttempt(examPublicId, appUserPrivateToken);

        return questions;
    }

    @MutationMapping
    public ExamAttemptResult endAttempt(@Argument SelectedAnswers selectedAnswers, @Argument Long attemptId, @Argument String appUserPrivateToken){
        System.out.println(selectedAnswers);
        return null;
    }
}
