package tomaszewskij.przedPraca.egzaminy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomaszewskij.przedPraca.egzaminy.services.AppUserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/v1/user")
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }



    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(HttpServletResponse response){
        Cookie cookie = new Cookie("cookieName", "cookieValue");
        cookie.setMaxAge(7 * 24 * 60 * 60);

        cookie.setS
        cookie.setSecure(true);
        cookie.setHttpOnly(true);


        response.addCookie(cookie);


        return ResponseEntity.ok("TEST");
    }


    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:3000")
    public String createUser(){
        return appUserService.createAppUser();
    }
}
