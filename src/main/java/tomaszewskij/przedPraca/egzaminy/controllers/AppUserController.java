package tomaszewskij.przedPraca.egzaminy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tomaszewskij.przedPraca.egzaminy.services.AppUserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @PostMapping("/login")
    public String login(@RequestBody String privateToken, HttpServletResponse response){

        Cookie cookie = new Cookie("cookieName", "cookieValue");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);


        return privateToken;
    }


    @PostMapping("/create")
    public String createUser(){
        return appUserService.createAppUser();
    }
}
