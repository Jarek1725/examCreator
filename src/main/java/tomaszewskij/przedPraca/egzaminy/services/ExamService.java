package tomaszewskij.przedPraca.egzaminy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import tomaszewskij.przedPraca.egzaminy.exceptions.NotFoundException;
import tomaszewskij.przedPraca.egzaminy.models.*;
import tomaszewskij.przedPraca.egzaminy.repositories.ExamRepository;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final AppUserService appUserService;
    private final QuestionService questionService;
    private final CategoryService categoryService;

    @Autowired
    public ExamService(ExamRepository examRepository, AppUserService appUserService, @Lazy QuestionService questionService, CategoryService categoryService) {
        this.examRepository = examRepository;
        this.appUserService = appUserService;
        this.questionService = questionService;
        this.categoryService = categoryService;
    }


    public void createExam(String examTitle, String appUserPrivateToken, List<String> categories) {
        Exam entity = new Exam(examTitle);
        List<Category> categoriesEntities = categories.stream().map(categoryService::findByNameOrCreateNew).toList();
        entity.setPublicId(generateRandomString(7));
        entity.setCreator(appUserService.getAppUserByPrivateToken(appUserPrivateToken));
        entity.setColorValue(ThreadLocalRandom.current().nextInt(0, 6));
        categoriesEntities.forEach(category -> {
            entity.addCategory(new ExamCategory(entity, category));
        });
        examRepository.save(entity);
    }


    public Exam getById(Long examId) {
        return examRepository
                .findById(examId)
                .orElseThrow(() -> new NotFoundException("Not found", "User"));
    }

    String generateRandomString(int limitNumber) {
        return new Random().ints(48, 126)
                .filter(i -> (i <= 48 || i >= 57) && (i <= 65 || i >= 90))
                .limit(limitNumber)
                .mapToObj(value -> String.valueOf((char) value).toUpperCase())
                .collect(Collectors.joining(""));
    }

    public List<Exam> getExams() {
        return examRepository.findAll();
    }

    public void addExamRating(double value, String privateToken, Long examId) {
        Exam exam = examRepository.findById(examId).orElseThrow(()->new NotFoundException("Exam with id: "+examId+", not found", ""));
        AppUser appUser = appUserService.getAppUserByPrivateToken(privateToken);

        exam.addExamRating(new ExamRating(appUser, exam, value));

        examRepository.save(exam);
    }
}
