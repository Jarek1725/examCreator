package tomaszewskij.przedPraca.egzaminy.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "University")
@Table(name = "university")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "university", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ExamUniversity> exams = new ArrayList<>();

    @Column(name = "city")
    private String city;

    @Column(name = "name")
    private String name;

    public University() {
    }

    public List<ExamUniversity> getExams() {
        return exams;
    }

    public University(String city, String name) {
        this.city = city;
        this.name = name;
    }

    public void setExams(List<ExamUniversity> exams) {
        this.exams = exams;
    }
}
