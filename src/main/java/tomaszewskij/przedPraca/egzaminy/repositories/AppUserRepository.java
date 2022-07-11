package tomaszewskij.przedPraca.egzaminy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tomaszewskij.przedPraca.egzaminy.DTO.PublicAppUserData;
import tomaszewskij.przedPraca.egzaminy.models.AppUser;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findAppUserByPrivateToken(String privateToken);

//    @Query("SELECT NEW tomaszewskij.przedPraca.egzaminy.controllers.POJO.PublicAppUserData(a.id, a.publicToken) FROM AppUser as a WHERE a.privateToken = ?1")
//    PublicAppUserData selectAppUserByPrivateTokenForLogin(String privateToken);

    @Query("SELECT a FROM AppUser as a WHERE a.privateToken = ?1")
    Optional<AppUser> selectAppUserByPrivateTokenForLogin(String privateToken);

}
