package tomaszewskij.przedPraca.egzaminy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tomaszewskij.przedPraca.egzaminy.models.Exam;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query(value = "SELECT e FROM Exam as e " +
            "LEFT JOIN e.categories as c " +
            "LEFT JOIN e.universities as u " +
            "LEFT JOIN e.attempts as a " +
            "LEFT JOIN e.examRatings as r " +
            "WHERE c.category.value LIKE (CASE WHEN ?1 IS NULL THEN '%' ELSE CONCAT('%',?1,'%')  END) " +
            "AND u.university.name LIKE (CASE WHEN ?2 IS NULL THEN '%' ELSE CONCAT('%',?2,'%') END)" +
            "GROUP BY e.id ORDER BY" +
            "(CASE WHEN ?3 LIKE '1' THEN e.title " +
            "WHEN ?3 LIKE '2' THEN COUNT(a.id) " +
            "WHEN ?3 LIKE '3' THEN AVG(r.value) " +
            "ELSE e.id END)")

    List<Exam> findAllExamsFilterAndSorted(String category, String school, String sortBy);

    Optional<Exam> findByPublicId(String publicId);
}
