package tomaszewskij.przedPraca.egzaminy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tomaszewskij.przedPraca.egzaminy.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
