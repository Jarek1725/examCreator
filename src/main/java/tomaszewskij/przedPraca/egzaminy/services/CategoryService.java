package tomaszewskij.przedPraca.egzaminy.services;

import org.springframework.stereotype.Service;
import tomaszewskij.przedPraca.egzaminy.exceptions.NotFoundException;
import tomaszewskij.przedPraca.egzaminy.models.Category;
import tomaszewskij.przedPraca.egzaminy.repositories.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void save(String categoryName) {
        categoryRepository.save(new Category(categoryName));
    }

    public Category findByNameOrCreateNew(String categoryName) {
//        Optional<Category> optionalCategory = categoryRepository.findByValue(categoryName);
//        Category category = null;
//        category = optionalCategory.isPresent() ? optionalCategory.orElse(null) : categoryRepository.save(new Category(categoryName));
//        return category;

        return categoryRepository.findByValue(categoryName).orElseGet(()->categoryRepository.save(new Category(categoryName)));
    }
}
