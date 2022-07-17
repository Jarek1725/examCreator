package tomaszewskij.przedPraca.egzaminy.models;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "AppUser")
@Table(name = "app_user")
public class AppUser {
    @Id
    @SequenceGenerator(
            name = "app_user_sequence",
            sequenceName = "app_user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "private_token",
            updatable = false
    )
    private String privateToken;

    @Column(
            name = "public_token",
            updatable = false
    )
    private String publicToken;

    @Column(
            name = "create_account",
            updatable = false
    )
    private LocalDate createDate = LocalDate.now();


    @OneToMany(
            mappedBy = "creator",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private List<Exam> exams = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ExamAttempts> examAttempts = new ArrayList<>();

    @OneToMany(mappedBy = "appUser", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ExamRating> examRatings = new ArrayList<>();

    public AppUser() {
    }

    public AppUser(String privateToken, String publicToken) {
        this.privateToken = privateToken;
        this.publicToken = publicToken;
    }

    public AppUser(Long id, String publicToken) {
        this.id = id;
        this.publicToken = publicToken;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrivateToken() {
        return privateToken;
    }

    public void setPrivateToken(String privateToken) {
        this.privateToken = privateToken;
    }

    public String getPublicToken() {
        return publicToken;
    }

    public void setPublicToken(String publicToken) {
        this.publicToken = publicToken;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public List<ExamRating> getExamRatings() {
        return examRatings;
    }

    public void setExamRatings(List<ExamRating> examRatings) {
        this.examRatings = examRatings;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public List<ExamAttempts> getExamAttempts() {
        return examAttempts;
    }

    public void setExamAttempts(List<ExamAttempts> examAttempts) {
        this.examAttempts = examAttempts;
    }

    public void addAttempt(ExamAttempts examAttempts) {
        this.examAttempts.add(examAttempts);
    }


}