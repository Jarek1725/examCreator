package tomaszewskij.przedPraca.egzaminy;

import tomaszewskij.przedPraca.egzaminy.models.AppUser;
import tomaszewskij.przedPraca.egzaminy.models.Exam;

public class ReturnOlnyPublicData {
    public static AppUser returnOnlyPublicAppUserData(AppUser appUser){
        appUser.setPrivateToken(null);
        appUser.getExams().forEach(
                exam -> {
                    exam.setId(null);
                }
        );
        return appUser;
    }

    public static Exam returnOnlyPublicExamData(Exam exam){
        exam.setCreator(null);
        return exam;
    }
}
