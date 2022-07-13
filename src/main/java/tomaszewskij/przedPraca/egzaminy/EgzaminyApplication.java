package tomaszewskij.przedPraca.egzaminy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tomaszewskij.przedPraca.egzaminy.exceptions.NotFoundException;
import tomaszewskij.przedPraca.egzaminy.models.*;
import tomaszewskij.przedPraca.egzaminy.repositories.*;

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
			QuestionRepository questionRepository
	){
		return args->{

			AppUser appUser = new AppUser("privateTokenTest", "publicTokenTest");
			Category category = new Category("Category name");
			Question question = new Question("Question value");
			Exam exam = new Exam("Title", appUser, List.of(question));


			exam.addCategory(new ExamCategory(exam, category));
			question.setExam(exam);


			categoryRepository.save(category);
			appUserRepository.save(appUser);
			questionRepository.save(question);
			examRepository.save(exam);

			Answer answer = new Answer("Answer value", questionRepository.findById(1L).orElseThrow(()->new NotFoundException(("YYY"))), false);

			answerRepository.save(answer);


		};
	};

}
