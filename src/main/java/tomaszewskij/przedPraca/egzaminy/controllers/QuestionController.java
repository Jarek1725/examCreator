package tomaszewskij.przedPraca.egzaminy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import tomaszewskij.przedPraca.egzaminy.DTO.AnswerInput;
import tomaszewskij.przedPraca.egzaminy.DTO.QuestionInput;
import tomaszewskij.przedPraca.egzaminy.models.Answer;
import tomaszewskij.przedPraca.egzaminy.models.Question;
import tomaszewskij.przedPraca.egzaminy.services.QuestionService;

import java.util.Iterator;

@Controller
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @MutationMapping
    public boolean addQuestionToExam(@Argument QuestionInput question) throws JSONException {

        System.out.println(question.getValue());
        System.out.println(question.getExamId());
        for (AnswerInput answer : question.getAnswers()) {
            System.out.println(answer.getValue());
            System.out.println(answer.getQuestionId());
        }

        questionService.saveQuestion(question);

        return true;
    }
}
