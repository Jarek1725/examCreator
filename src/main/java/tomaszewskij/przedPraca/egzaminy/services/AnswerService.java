package tomaszewskij.przedPraca.egzaminy.services;

import org.springframework.stereotype.Service;
import tomaszewskij.przedPraca.egzaminy.exceptions.NotFoundException;
import tomaszewskij.przedPraca.egzaminy.models.Answer;
import tomaszewskij.przedPraca.egzaminy.repositories.AnswerRepository;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer findById(Long id){
        return answerRepository.findById(id).orElseThrow(()->new NotFoundException("Not found answer with id: "+id, "Answer"));
    }
}
