package tomaszewskij.przedPraca.egzaminy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tomaszewskij.przedPraca.egzaminy.services.AppUserService;

@RestController
@RequestMapping("api/v1/user")
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @PostMapping("/create")
    public String createUser(){
        return appUserService.createAppUser();
    }
}
