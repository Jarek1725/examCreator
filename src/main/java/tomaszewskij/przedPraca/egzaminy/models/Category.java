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
    private List<ExamCategory> categories = new ArrayList<>();

    public Category() {
    }

    public Category(String value, List<ExamCategory> categories) {
        this.value = value;
        this.categories = categories;
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

    public List<ExamCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ExamCategory> categories) {
        this.categories = categories;
    }
}
