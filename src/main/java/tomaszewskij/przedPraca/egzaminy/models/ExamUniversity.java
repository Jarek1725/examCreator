package tomaszewskij.przedPraca.egzaminy.models;

import javax.persistence.*;

@Entity(name = "ExamUniversity")
@Table(name = "exam_university")
public class ExamUniversity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;


    public ExamUniversity() {
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
