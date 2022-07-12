package tomaszewskij.przedPraca.egzaminy.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "examsCategories")
@Table(name = "exams_categories")
public class ExamCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exam_id")
    private Exam exam;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    public ExamCategory() {
    }
}
