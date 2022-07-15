package tomaszewskij.przedPraca.egzaminy.models;

import javax.persistence.*;

@Entity(name = "ExamRating")
@Table(name = "exam_rating")
public class ExamRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Column(name = "value")
    private Double value;

    public ExamRating() {
    }

    public ExamRating(AppUser appUser, Exam exam, Double value) {
        this.appUser = appUser;
        this.exam = exam;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
