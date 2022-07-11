package tomaszewskij.przedPraca.egzaminy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Fetch;
import tomaszewskij.przedPraca.egzaminy.DTO.PublicAppUserData;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



@Entity(name = "AppUser")
@Table(name = "app_user")
public class AppUser {

    @JsonIgnore
    @Id
    @SequenceGenerator(
            name = "app_user_sequence",
            sequenceName = "app_user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "private_token",
            updatable = false
    )
    private String privateToken;

    @Column(
            name = "public_token",
            updatable = false
    )
    private String publicToken;

    @Column(
            name="create_account",
            updatable = false
    )
    private Date createDate;

    @OneToMany(
            mappedBy = "creator"
    )
    private List<Exam> exams = new ArrayList<>();



    public AppUser() {
    }

    public AppUser(String privateToken, String publicToken) {
        this.privateToken = privateToken;
        this.publicToken = publicToken;
    }

    public AppUser(Long id, String publicToken) {
        this.id = id;
        this.publicToken = publicToken;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrivateToken() {
        return privateToken;
    }

    public void setPrivateToken(String privateToken) {
        this.privateToken = privateToken;
    }

    public String getPublicToken() {
        return publicToken;
    }

    public void setPublicToken(String publicToken) {
        this.publicToken = publicToken;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}