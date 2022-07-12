package tomaszewskij.przedPraca.egzaminy.services;

import org.springframework.stereotype.Service;
import tomaszewskij.przedPraca.egzaminy.repositories.QuestionRepository;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
}
