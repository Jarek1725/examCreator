package tomaszewskij.przedPraca.egzaminy.DTO;

import java.util.ArrayList;
import java.util.List;

public class SelectedAnswers {
    private Long examId;
    private List<Long> selectedAnswers = new ArrayList<>();

    public SelectedAnswers(Long examId, List<Long> selectedAnswers) {
        this.examId = examId;
        this.selectedAnswers = selectedAnswers;
    }

    public Long getExamId() {
        return examId;
    }

    public List<Long> getSelectedAnswers() {
        return selectedAnswers;
    }

    @Override
    public String toString() {
        return "SelectedAnswers{" +
                "examId=" + examId +
                ", selectedAnswers=" + selectedAnswers +
                '}';
    }
}
