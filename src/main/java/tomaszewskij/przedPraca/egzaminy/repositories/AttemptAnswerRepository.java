package tomaszewskij.przedPraca.egzaminy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tomaszewskij.przedPraca.egzaminy.models.AttemptAnswer;

public interface AttemptAnswerRepository extends JpaRepository<AttemptAnswer, Long> {
}
