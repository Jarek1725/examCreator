package tomaszewskij.przedPraca.egzaminy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import tomaszewskij.przedPraca.egzaminy.models.AppUser;
import tomaszewskij.przedPraca.egzaminy.repositories.AppUserRepository;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Random;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public String createAppUser(){
        String privateToken = generateRandomString(12);
        String publicToken = generateRandomString(6);

        appUserRepository.save(new AppUser(privateToken, publicToken));

        return privateToken;
    }

    String generateRandomString(int limitNumber){
        int leftLimit = 48;
        int rightLimit = 122;

        return new Random().ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(limitNumber)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString()
                .toUpperCase();
    }
}
