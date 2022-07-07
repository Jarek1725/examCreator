package tomaszewskij.przedPraca.egzaminy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tomaszewskij.przedPraca.egzaminy.models.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {



}
