package tomaszewskij.przedPraca.egzaminy.models;


import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Question")
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "value",
            nullable = false,
            updatable = false
    )
    private String value;

    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL
    )
    private List<Answer> answers = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "exam_id"
    )
    private Exam exam;

    @Column(name = "isHidden")
    private Boolean isHidden = false;

    @Column(name = "points")
    private Integer points;

    public Question() {
    }

    public Question(String value, Integer points) {
        this.value = value;
        this.points = points;
    }



    public Question(String value, List<Answer> answers, Integer points) {
        this.value = value;
        this.answers = answers;
        this.points = points;
    }

    public Question(String value, List<Answer> answers, Exam exam) {
        this.value = value;
        this.answers = answers;
        this.exam = exam;
    }

    public Question(String value, Exam exam, Integer points) {
        this.value = value;
        this.exam = exam;
        this.points = points;
    }

    public Question(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Long getId() {
        return id;
    }

    public Boolean isHidden() {
        return isHidden;
    }

    public void setHidden(Boolean hidden) {
        isHidden = hidden;
    }



    public Boolean getHidden() {
        return isHidden;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void addAnswer(Answer answer){
        this.answers.add(answer);
    }

    @Override
    public String toString() {
        return "Question{" +
                "value='" + value + '\'' +
                '}';
    }
}
