package tomaszewskij.przedPraca.egzaminy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tomaszewskij.przedPraca.egzaminy.ReturnOlnyPublicData;
import tomaszewskij.przedPraca.egzaminy.exceptions.NotFoundException;
import tomaszewskij.przedPraca.egzaminy.models.AppUser;
import tomaszewskij.przedPraca.egzaminy.repositories.AppUserRepository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

    public AppUser getAppUserByPrivateToken(String privateToken) {
        return appUserRepository.findAppUserByPrivateToken(privateToken).orElseThrow(
                ()->new NotFoundException("Not found", "User")
        );
    }

    public List<AppUser> getAllAppUsers() {
        return appUserRepository.findAll().stream().map(ReturnOlnyPublicData::returnOnlyPublicAppUserData).toList();
    }

    public AppUser login(String privateToken) {
        AppUser appUser = appUserRepository.selectAppUserByPrivateTokenForLogin(privateToken).orElseThrow(                ()->new NotFoundException("Not found", "User"));
        return appUser;
    }


    String generateRandomString(int limitNumber) {
        return new Random().ints(48, 126)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(limitNumber)
                .mapToObj(value -> String.valueOf((char) value).toUpperCase())
                .collect(Collectors.joining(""));
    }
}
