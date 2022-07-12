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


    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<ExamCategory> examCategories = new ArrayList<>();


    public Exam() {
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
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
}
