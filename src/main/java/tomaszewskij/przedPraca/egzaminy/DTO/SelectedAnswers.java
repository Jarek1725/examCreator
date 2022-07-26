package tomaszewskij.przedPraca.egzaminy.DTO;

import java.util.ArrayList;
import java.util.List;

public class SelectedAnswers {
    private Long questionId;
    private List<Long> selectedAnswersId = new ArrayList<>();

    public SelectedAnswers(Long questionId, List<Long> selectedAnswersId) {
        this.questionId = questionId;
        this.selectedAnswersId = selectedAnswersId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public List<Long> getSelectedAnswersId() {
        return selectedAnswersId;
    }
}
