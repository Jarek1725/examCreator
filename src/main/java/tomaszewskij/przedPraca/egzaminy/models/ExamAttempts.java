package tomaszewskij.przedPraca.egzaminy.models;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

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

    @Column(name = "date")
    private LocalDate createDate = LocalDate.now();



    public ExamAttempts() {
    }

    public ExamAttempts(int score, AppUser user, Exam exam) {
        this.score = score;
        this.user = user;
        this.exam = exam;
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

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}
