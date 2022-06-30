package tomaszewskij.przedPraca.egzaminy.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("api/v1")
public class ExamController {

    @GetMapping("/test")
    String test(){
        return "TEST";
    }
}
