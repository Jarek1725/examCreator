package tomaszewskij.przedPraca.egzaminy.DTO;

public class ExamAttemptFilter {
    private String publicToken;
    private Long examId;

    public ExamAttemptFilter(String publicToken, Long examId) {
        this.publicToken = publicToken;
        this.examId = examId;
    }

    public String getPublicToken() {
        return publicToken;
    }

    public void setPublicToken(String publicToken) {
        this.publicToken = publicToken;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }
}
