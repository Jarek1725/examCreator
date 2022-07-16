package tomaszewskij.przedPraca.egzaminy.services;

import org.springframework.stereotype.Service;
import tomaszewskij.przedPraca.egzaminy.DTO.QuestionInput;
import tomaszewskij.przedPraca.egzaminy.models.Answer;
import tomaszewskij.przedPraca.egzaminy.models.Question;
import tomaszewskij.przedPraca.egzaminy.repositories.ExamRepository;
import tomaszewskij.przedPraca.egzaminy.repositories.QuestionRepository;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final ExamService examService;

    public QuestionService(QuestionRepository questionRepository, ExamService examService) {
        this.questionRepository = questionRepository;
        this.examService = examService;
    }

    public void saveQuestion(QuestionInput questionInput) {
        Question question = new Question();
        question.setExam(examService.getById(questionInput.getExamId()));
//        questionInput.getAnswers().forEach(answer->{
//            question.addAnswer(new Answer(answer.getValue(), question, answer.isCorrect()));
//        });

        question.setValue(questionInput.getValue());
        question.setPoints(questionInput.getPoints());


        questionRepository.save(question);
    }
}
