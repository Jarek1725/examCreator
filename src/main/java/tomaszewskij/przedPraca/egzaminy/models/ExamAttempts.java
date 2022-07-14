package tomaszewskij.przedPraca.egzaminy.models;

import javax.persistence.*;

@Entity(name = "ExamAttempts")
@Table(name = "exam_attempts")
public class ExamAttempts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "score")
    private int score;

    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )

    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    public ExamAttempts() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
