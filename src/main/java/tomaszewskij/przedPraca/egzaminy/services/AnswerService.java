package tomaszewskij.przedPraca.egzaminy.services;

import org.springframework.stereotype.Service;
import tomaszewskij.przedPraca.egzaminy.repositories.AnswerRepository;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }
}
