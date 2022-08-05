package tomaszewskij.przedPraca.egzaminy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tomaszewskij.przedPraca.egzaminy.DTO.AnswerInput;
import tomaszewskij.przedPraca.egzaminy.DTO.QuestionInput;
import tomaszewskij.przedPraca.egzaminy.exceptions.NotFoundException;
import tomaszewskij.przedPraca.egzaminy.models.*;
import tomaszewskij.przedPraca.egzaminy.repositories.*;
import tomaszewskij.przedPraca.egzaminy.services.AppUserService;
import tomaszewskij.przedPraca.egzaminy.services.CategoryService;
import tomaszewskij.przedPraca.egzaminy.services.ExamService;
import tomaszewskij.przedPraca.egzaminy.services.QuestionService;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class EgzaminyApplication {
    public static void main(String[] args) {
        SpringApplication.run(EgzaminyApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            AppUserRepository appUserRepository,
            AnswerRepository answerRepository,
            CategoryRepository categoryRepository,
            ExamRepository examRepository,
            QuestionRepository questionRepository,
            AppUserService appUserService,
            ExamService examService,
            CategoryService categoryService,
            QuestionService questionService
    ) {
        return args -> {
            if (1 == 3) {

                String appUserPrivateString = appUserService.createAppUser();
                Exam exam = examService.createExam("Opus Magnum", appUserPrivateString, List.of("Programowanie"), 1L);

                Question question1 = new Question("Wskaż poniżej poprawne definicje tablic", exam, 1);
                Question question2 = new Question("Czy poprawna jest następująca definicja?", exam, 1);
                Question question3 = new Question("Mamy tablicą:char tabl[10] = { 'ABCD' };  Czy poprawne są następujące instrukcje korzystające  z tej tablicy??", exam, 2);

                List<Answer> answers1 = List.of(
                        new Answer("double wspolcz[5.6]", question1, false),
                        new Answer("signed int nomin[-6]", question1, false),
                        new Answer("double fact[0]", question1, false),
                        new Answer("Żadna odpowiedz nie jest poprawna", question1, true)
                );

                List<Answer> answers2 = List.of(
                        new Answer("Tak", question2, false),
                        new Answer("Nie", question2, true)
                );


                List<Answer> answers3 = List.of(
                        new Answer("tabl[6] = 'm';", question3, true),
                        new Answer("tabl[10] = { \"GGGG\" };", question3, false),
                        new Answer("tabl[0] = { \"GGGG\" };", question3, false),
                        new Answer("tabl[5] = \"a\";", question3, false),
                        new Answer("tabl[  ] = 'a';", question3, false),
                        new Answer("tabl[  ] = 'a';", question3, false),
                        new Answer("tabl  = \"7\"", question3, false)
                );

                question1.setAnswers(answers1);
                question2.setAnswers(answers2);
                question3.setAnswers(answers3);


                University university = new University("Kraków", "Politechnika Krakowska");

                ExamUniversity examUniversity = new ExamUniversity(exam, university);

                exam.setUniversities(List.of(examUniversity));
                exam.setQuestions(List.of(question1, question2, question3));
                examRepository.save(exam);
            }
            ;
        };

    }

    ;

}
