package tomaszewskij.przedPraca.egzaminy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tomaszewskij.przedPraca.egzaminy.models.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
