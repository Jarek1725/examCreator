package tomaszewskij.przedPraca.egzaminy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tomaszewskij.przedPraca.egzaminy.models.AppUser;
import tomaszewskij.przedPraca.egzaminy.repositories.AppUserRepository;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public String createAppUser() {
        String privateToken = generateRandomString(12);
        String publicToken = generateRandomString(6);

        appUserRepository.save(new AppUser(privateToken, publicToken));

        return privateToken;
    }

    String generateRandomString(int limitNumber) {
        return new Random().ints(48, 126)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(limitNumber)
                .mapToObj(value -> String.valueOf((char)value).toUpperCase())
                .collect(Collectors.joining(""));


//        String generatedString = random.ints(leftLimit, rightLimit + 1)
//                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
//                .limit(targetStringLength)
//                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                .toString();
    }
}
