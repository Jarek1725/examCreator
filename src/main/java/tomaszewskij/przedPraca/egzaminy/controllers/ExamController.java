package tomaszewskij.przedPraca.egzaminy.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tomaszewskij.przedPraca.egzaminy.DatabaseProps;

import java.util.Map;

@RestController

@RequestMapping("api/v1")
public class ExamController {

    private final DatabaseProps databaseProps;

    public ExamController(DatabaseProps databaseProps) {
        this.databaseProps = databaseProps;
    }

    @GetMapping("/test")
    public Map<String, String> test(){
        return Map.of(
                "URL", databaseProps.url(),
                "Login", databaseProps.login(),
                "Pass", databaseProps.pass()
        );
    }
}
