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

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<ExamCategory> categories = new ArrayList<>();

    public Category() {
    }
}
