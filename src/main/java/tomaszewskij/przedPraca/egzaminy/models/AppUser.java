package tomaszewskij.przedPraca.egzaminy.models;

import javax.persistence.*;
import java.sql.Date;

//
@Entity(name = "AppUser")
@Table(name = "app_user")
public class AppUser {
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
    private Date createdAccount;


    public AppUser() {
    }

    public AppUser(String privateToken, String publicToken) {
        this.privateToken = privateToken;
        this.publicToken = publicToken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}