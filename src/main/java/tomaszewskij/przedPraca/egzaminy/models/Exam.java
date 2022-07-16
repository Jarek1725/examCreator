package tomaszewskij.przedPraca.egzaminy.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity(name = "Exam")
@Table(name = "exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id")
    private String publicId;

    @Column(
            name = "title",
            nullable = false,
            updatable = false
    )
    private String title;

    @ManyToOne
    @JoinColumn(name = "exam_creator_id")
    private AppUser creator;

    @OneToMany(mappedBy = "exam")
    private List<Question> questions = new ArrayList<>();

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "exam"
    )
    private List<ExamCategory> categories = new ArrayList<>();

    @Column(name = "color_value")
    private int colorValue;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "exam"
    )
    private List<ExamAttempts> attempts = new ArrayList<>();

    @Column(name = "create_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createDate = LocalDate.now();

    @OneToMany(mappedBy = "exam", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ExamRating> examRatings = new ArrayList<>();


    private double averageScore;

    private Long maxPoints;

    private double averageExamRating;


    public Exam() {
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Exam(String title, AppUser creator, List<Question> questions) {
        this.title = title;
        this.creator = creator;
        this.questions = questions;
    }


    public Exam(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AppUser getCreator() {
        return creator;
    }

    public void setCreator(AppUser creator) {
        this.creator = creator;
    }

    public List<Category> getCategories() {
        return this.categories.stream().map(ExamCategory::getCategory).toList();
    }

    public void setCategories(List<ExamCategory> categories) {
        this.categories = categories;
    }

    public void addCategory(ExamCategory examCategory) {
        this.categories.add(examCategory);
    }

    public int getColorValue() {
        return colorValue;
    }

    public void setColorValue(int colorValue) {
        this.colorValue = colorValue;
    }

    public List<ExamAttempts> getAttempts() {
        return attempts;
    }

    public void setAttempts(List<ExamAttempts> attempts) {
        this.attempts = attempts;
    }

    public double getAverageScore() {
//        return attempts.stream().mapToDouble(ExamAttempts::getScore).average().orElse(0);
        double averageScore = attempts.stream().mapToDouble(ExamAttempts::getScore).average().orElse(0);
        long maxPoints = questions.stream().mapToLong(Question::getPoints).sum();
        if (maxPoints == 0) {
            maxPoints = 1;
        }
        return Math.round((averageScore / maxPoints) * 100 * 100.0) / 100.0;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public Long getMaxPoints() {
        return questions.stream().mapToLong(Question::getPoints).sum();
    }

    public void setMaxPoints(Long maxPoints) {
        this.maxPoints = maxPoints;
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

    public double getAverageExamRating() {
        return examRatings.stream().mapToDouble(ExamRating::getValue).average().orElse(0);
    }

    public void addExamRating(ExamRating examRating) {
        this.examRatings.add(examRating);
    }

    public void addExamAttempt(ExamAttempts examAttempts) {
        this.attempts.add(examAttempts);
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creator=" + creator +
                ", questions=" + questions +
                ", categories=" + categories +
                '}';
    }
}
