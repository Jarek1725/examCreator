package tomaszewskij.przedPraca.egzaminy.models;

//import javax.persistence.*;

//@Entity(name = "Exam")
//@Table(name = "exam")
public class Exam {
//    @Id
//    @SequenceGenerator(
//            name = "exam_sequence",
//            sequenceName = "exam_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "exam_sequence"
//    )
//    @Column(
//            name = "id",
//            updatable = false
//    )
    private Long id;

//    @Column(
//            name = "title",
//            nullable = false,
//            updatable = false
//    )
    private String title;

    public Exam() {
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
}
