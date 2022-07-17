package tomaszewskij.przedPraca.egzaminy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tomaszewskij.przedPraca.egzaminy.DTO.ExamAttemptFilter;
import tomaszewskij.przedPraca.egzaminy.models.ExamAttempts;
import tomaszewskij.przedPraca.egzaminy.repositories.ExamAttemptRepository;

import java.util.List;

@Service
public class ExamAttemptService {
    private final ExamAttemptRepository examAttemptRepository;

    @Autowired
    public ExamAttemptService(ExamAttemptRepository examAttemptRepository) {
        this.examAttemptRepository = examAttemptRepository;
    }

    public List<ExamAttempts> getExams(ExamAttemptFilter filter) {
        return examAttemptRepository.findAllFilterAndGroup(filter.getExamId(), filter.getPublicToken());
    }
}
