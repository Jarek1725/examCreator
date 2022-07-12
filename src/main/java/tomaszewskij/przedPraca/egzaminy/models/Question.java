package tomaszewskij.przedPraca.egzaminy.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Question")
@Table(name = "question")
public class Question {
    @Id
    @SequenceGenerator(
            name = "question_sequence",
            sequenceName = "question_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "question_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "value",
            nullable = false,
            updatable = false
    )
    private String value;

    @OneToMany(
            mappedBy = "question"
    )
    private List<Answer> answers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(
            name = "exam_id"
    )
    private Exam exam;

    public Question() {
    }
}
