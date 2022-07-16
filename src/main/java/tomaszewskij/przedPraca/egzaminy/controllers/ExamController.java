package tomaszewskij.przedPraca.egzaminy.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tomaszewskij.przedPraca.egzaminy.models.Exam;
import tomaszewskij.przedPraca.egzaminy.services.ExamService;

import java.util.List;

@Controller
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @MutationMapping
    public boolean createExam(@Argument String examTitle, @Argument String appUserPrivateToken, @Argument List<String> categories){
        examService.createExam(examTitle, appUserPrivateToken, categories);
        return true;
    }

    @MutationMapping
    public boolean addExamRating(@Argument double value, @Argument String appUserPrivateToken, @Argument Long examId){
        examService.addExamRating(value, appUserPrivateToken, examId);
        return true;
    }

    @MutationMapping
    public boolean addExamAttempt(@Argument int value, @Argument String appUserPrivateToken, @Argument Long examId){
        examService.addExamAttempt(value, appUserPrivateToken, examId);
        return true;
    }

    @QueryMapping
    public Exam getExam(@Argument Long examId){
        return examService.getById(examId);
    }

    @QueryMapping
    public List<Exam> getExams(){
        return examService.getExams();
    }

}
