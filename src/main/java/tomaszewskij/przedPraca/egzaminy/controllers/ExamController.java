package tomaszewskij.przedPraca.egzaminy.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tomaszewskij.przedPraca.egzaminy.services.ExamService;

@RestController
@RequestMapping("api/v1")
public class ExamController {

    private final ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @MutationMapping
    public boolean createExam(@Argument String examTitle, @Argument String appUserPrivateToken){
        examService.createExam(examTitle, appUserPrivateToken);
        return true;
    }


}
