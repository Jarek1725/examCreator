package tomaszewskij.przedPraca.egzaminy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import tomaszewskij.przedPraca.egzaminy.DTO.ExamAttemptFilter;
import tomaszewskij.przedPraca.egzaminy.models.ExamAttempts;
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
    public List<ExamAttempts> getExamAttempts(@Argument ExamAttemptFilter filter){
        return examAttemptService.getExams(filter);
    }
}
