package tomaszewskij.przedPraca.egzaminy.DTO;

import tomaszewskij.przedPraca.egzaminy.models.Answer;

import java.util.ArrayList;
import java.util.List;

public class QuestionInput {
    private String value;
    private Long examId;
    private List<AnswerInput> answers = new ArrayList<>();

    public QuestionInput(String value, Long examId, List<AnswerInput> answers) {
        this.value = value;
        this.examId = examId;
        this.answers = answers;
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
