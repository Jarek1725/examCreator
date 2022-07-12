package tomaszewskij.przedPraca.egzaminy.services;

import org.springframework.stereotype.Service;
import tomaszewskij.przedPraca.egzaminy.repositories.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
