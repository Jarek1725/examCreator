package tomaszewskij.przedPraca.egzaminy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import tomaszewskij.przedPraca.egzaminy.DTO.ExamFilter;
import tomaszewskij.przedPraca.egzaminy.exceptions.NotFoundException;
import tomaszewskij.przedPraca.egzaminy.models.*;
import tomaszewskij.przedPraca.egzaminy.repositories.ExamRepository;

import java.util.Collections;
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
        entity.setPublicId(generateRandomString());
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

    String generateRandomString() {
        return new Random().ints(48, 91)
                .filter(i -> (i >= 48 && i <= 57) || (i >= 65 && i <= 91))
                .limit(6)
                .mapToObj(value -> String.valueOf((char) value))
                .collect(Collectors.joining(""));
    }

    public List<Exam> getExams(ExamFilter filter, String sortBy) {
        List<Exam> allExamsFilterAndSorted = examRepository.findAllExamsFilterAndSorted(filter.getCategory(), filter.getSchool(), sortBy);
        if (sortBy != null) {
            if (sortBy.equals("2") || sortBy.equals("3")) {
                Collections.reverse(allExamsFilterAndSorted);
                allExamsFilterAndSorted.forEach(System.out::println);
            }
        }
        return allExamsFilterAndSorted;
    }

    public void addExamRating(double value, String appUserPrivateToken, Long examId) {
        Exam exam = examRepository.findById(examId).orElseThrow(() -> new NotFoundException("Exam with id: " + examId + ", not found", ""));
        AppUser appUser = appUserService.getAppUserByPrivateToken(appUserPrivateToken);

        exam.addExamRating(new ExamRating(appUser, exam, value));

        examRepository.save(exam);
    }


    public void addExamAttempt(int score, String privateToken, Long examId) {
        Exam exam = examRepository.findById(examId).orElseThrow(() -> new NotFoundException("Exam with id: " + examId + ", not found", ""));
        AppUser appUser = appUserService.getAppUserByPrivateToken(privateToken);

        exam.addExamAttempt(new ExamAttempts(score, appUser, exam));

        examRepository.save(exam);
    }
}
