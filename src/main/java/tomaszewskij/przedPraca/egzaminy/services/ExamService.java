package tomaszewskij.przedPraca.egzaminy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tomaszewskij.przedPraca.egzaminy.models.AppUser;
import tomaszewskij.przedPraca.egzaminy.models.Exam;
import tomaszewskij.przedPraca.egzaminy.repositories.ExamRepository;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final AppUserService appUserService;

    @Autowired
    public ExamService(ExamRepository examRepository, AppUserService appUserService) {
        this.examRepository = examRepository;
        this.appUserService = appUserService;
    }


    public void createExam(String examTitle, String appUserPrivateToken) {
        Exam entity = new Exam(examTitle);
        entity.setCreator(appUserService.getAppUserByPrivateToken(appUserPrivateToken));
        examRepository.save(entity);
    }


}
