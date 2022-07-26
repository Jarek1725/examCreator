package tomaszewskij.przedPraca.egzaminy.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Answer")
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "value"
    )
    private String value;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "question_id"
    )
    private Question question;

    @Column(
            name = "is_correct"
    )
    private boolean isCorrect;

    @OneToMany(mappedBy = "answer")
    private List<AttemptAnswer> attemptAnswers = new ArrayList<>();

    public Answer() {
    }

    public Answer(String value, boolean isCorrect) {
        this.value = value;
        this.isCorrect = isCorrect;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public Answer(String value, Question question, boolean isCorrect) {
        this.value = value;
        this.question = question;
        this.isCorrect = isCorrect;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public List<AttemptAnswer> getAttemptAnswers() {
        return attemptAnswers;
    }

    public void setAttemptAnswers(List<AttemptAnswer> attemptAnswers) {
        this.attemptAnswers = attemptAnswers;
    }
}
