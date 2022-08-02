package tomaszewskij.przedPraca.egzaminy.DTO;

import tomaszewskij.przedPraca.egzaminy.models.Question;

import java.util.ArrayList;
import java.util.List;

public class StartAttempt {
    List<Question> questions = new ArrayList<>();
    Long attemptId;

    public StartAttempt(List<Question> questions, Long attemptId) {
        this.questions = questions;
        this.attemptId = attemptId;
    }

    public List<Question> getQuestionList() {
        return questions;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questions = questionList;
    }

    public Long getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(Long attemptId) {
        this.attemptId = attemptId;
    }
}
