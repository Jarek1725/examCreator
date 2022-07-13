package tomaszewskij.przedPraca.egzaminy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity(name = "Exam")
@Table(name = "exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


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


    public Exam() {
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

    public List<ExamCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ExamCategory> categories) {
        this.categories = categories;
    }

    public void addCategory(ExamCategory examCategory) {
        System.out.println("CATEGORY ADDED");
        this.categories.add(examCategory);
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
