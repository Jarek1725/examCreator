package tomaszewskij.przedPraca.egzaminy.DTO;

public class AnswerInput {
    private String value;
    private Long questionId;
    private boolean isCorrect;

    public AnswerInput(String value, Long questionId, boolean isCorrect) {
        this.value = value;
        this.questionId = questionId;
        this.isCorrect = isCorrect;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
