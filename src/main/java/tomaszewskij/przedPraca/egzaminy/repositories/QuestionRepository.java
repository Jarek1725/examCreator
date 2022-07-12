package tomaszewskij.przedPraca.egzaminy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tomaszewskij.przedPraca.egzaminy.models.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
