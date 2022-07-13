package tomaszewskij.przedPraca.egzaminy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
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
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "exam"
    )
    private List<ExamCategory> categories = new ArrayList<>();

    @Column(name = "color_value")
    private int colorValue;


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
