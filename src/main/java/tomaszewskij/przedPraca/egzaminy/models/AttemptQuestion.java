package tomaszewskij.przedPraca.egzaminy.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "AttemptQuestion")
@Table(name = "attempt_question")
public class AttemptQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "attempt_id")
    private ExamAttempts examAttempts;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(mappedBy = "attemptQuestion")
    private List<AttemptAnswer> attemptAnswers = new ArrayList<>();

    public AttemptQuestion() {
    }

    public ExamAttempts getExamAttempts() {
        return examAttempts;
    }

    public void setExamAttempts(ExamAttempts examAttempts) {
        this.examAttempts = examAttempts;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<AttemptAnswer> getAttemptAnswers() {
        return attemptAnswers;
    }

    public void setAttemptAnswers(List<AttemptAnswer> attemptAnswers) {
        this.attemptAnswers = attemptAnswers;
    }
}
