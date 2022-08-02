package tomaszewskij.przedPraca.egzaminy.DTO;

import tomaszewskij.przedPraca.egzaminy.models.Question;

import java.util.ArrayList;
import java.util.List;

public class AttemptResult {
    private Long id;
    private List<Question> questions = new ArrayList<>();
    private double points;
}
