package tomaszewskij.przedPraca.egzaminy.DTO;

import tomaszewskij.przedPraca.egzaminy.models.Exam;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class PublicAppUserData implements Serializable {
    private Long id;
    private String publicToken;
    private List<Exam> examCollection = new ArrayList<>();


    public PublicAppUserData(Long id, String publicToken, List<Exam> examCollection) {
        this.id = id;
        this.publicToken = publicToken;
        this.examCollection = examCollection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublicToken() {
        return publicToken;
    }

    public void setPublicToken(String publicToken) {
        this.publicToken = publicToken;
    }
}
