package tomaszewskij.przedPraca.egzaminy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import tomaszewskij.przedPraca.egzaminy.models.AppUser;
import tomaszewskij.przedPraca.egzaminy.services.AppUserService;

import java.util.List;

@Controller
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @QueryMapping
    public AppUser login(@Argument String privateToken) {

//        Cookie cookie = new Cookie("cookieName", "cookieValue");
//        cookie.setMaxAge(7 * 24 * 60 * 60);
//        cookie.setSecure(true);
//        cookie.setHttpOnly(true);
//        response.addCookie(cookie);

        return appUserService.login(privateToken);
    }


    //    @PostMapping("/create")
    @MutationMapping
    public String createAppUser() {
        return appUserService.createAppUser();
    }


    @QueryMapping
    public List<AppUser> appUsers() {
        return appUserService.getAllAppUsers();
    }
}
