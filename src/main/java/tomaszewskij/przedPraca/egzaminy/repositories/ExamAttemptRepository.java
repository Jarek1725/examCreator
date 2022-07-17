package tomaszewskij.przedPraca.egzaminy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tomaszewskij.przedPraca.egzaminy.models.Exam;
import tomaszewskij.przedPraca.egzaminy.models.ExamAttempts;

import java.util.List;

@Repository
public interface ExamAttemptRepository extends JpaRepository<ExamAttempts, Long> {
    @Query(
            "SELECT e FROM ExamAttempts as e WHERE e.exam.id LIKE (CASE WHEN ?1 IS NULL THEN '%' ELSE ?1 END) " +
            "AND e.user.publicToken LIKE (CASE WHEN ?2 IS NULL THEN '%' ELSE ?2 END)"
    )
    List<ExamAttempts> findAllFilterAndGroup(Long examId, String publicToken);
}
