package tomaszewskij.przedPraca.egzaminy.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Category")
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "value"
    )
    private String value;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "category"
    )
    private List<ExamCategory> exams = new ArrayList<>();

    public Category() {
    }

    public Category(String value, List<ExamCategory> exams) {
        this.value = value;
        this.exams = exams;
    }

    public Category(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void addExam(ExamCategory examCategory){
        this.exams.add(examCategory);
    }

    public List<Exam> getExams() {
        return this.exams.stream().map(ExamCategory::getExam).toList();
    }

    public void setExams(List<ExamCategory> exams) {
        this.exams = exams;
    }
}
