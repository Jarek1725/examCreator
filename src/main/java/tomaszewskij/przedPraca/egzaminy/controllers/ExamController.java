package tomaszewskij.przedPraca.egzaminy.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController

@RequestMapping("api/v1")
public class ExamController {

    @GetMapping("/test")
    public String test(){
        return "TEST";
    }
}
