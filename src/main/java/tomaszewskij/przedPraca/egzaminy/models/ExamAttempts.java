package tomaszewskij.przedPraca.egzaminy.models;

import javax.persistence.*;

@Entity(name = "ExamAttempts")
@Table(name = "exam_attempts")
public class ExamAttempts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "score")
    private int score;

    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    private AppUser user;
}
