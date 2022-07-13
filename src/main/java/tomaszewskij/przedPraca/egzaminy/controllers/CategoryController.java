package tomaszewskij.przedPraca.egzaminy.controllers;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import tomaszewskij.przedPraca.egzaminy.services.CategoryService;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @MutationMapping
    public boolean createCategory(@Argument String categoryName){
        categoryService.save(categoryName);
        return true;
    }
}
