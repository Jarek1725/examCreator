package tomaszewskij.przedPraca.egzaminy.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "examsCategories")
@Table(name = "exams_categories")
public class ExamCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "exam_id",
            foreignKey = @ForeignKey(
                    name = "exam_id_fk"
            )
    )
    private Exam exam;

    @ManyToOne
    @JoinColumn(
            name = "category_id",
            foreignKey = @ForeignKey(
                    name = "category_id_fk"
            )
    )
    private Category category;

    public ExamCategory() {
    }

    public ExamCategory(Exam exam, Category category) {
        this.exam = exam;
        this.category = category;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
