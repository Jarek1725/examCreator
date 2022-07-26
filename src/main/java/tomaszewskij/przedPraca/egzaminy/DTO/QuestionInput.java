package tomaszewskij.przedPraca.egzaminy.DTO;

import tomaszewskij.przedPraca.egzaminy.models.Answer;

import java.util.ArrayList;
import java.util.List;

public class QuestionInput {
    private String value;
    private Long examId;
    private List<AnswerInput> answers = new ArrayList<>();
    private Integer points;

    public QuestionInput(String value, Long examId, List<AnswerInput> answers, Integer points) {
        this.value = value;
        this.examId = examId;
        this.answers = answers;
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public List<AnswerInput> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerInput> answers) {
        this.answers = answers;
    }
}
