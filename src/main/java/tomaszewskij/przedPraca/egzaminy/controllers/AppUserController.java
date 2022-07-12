package tomaszewskij.przedPraca.egzaminy.controllers;

import graphql.ExecutionInput;
import graphql.GraphQLContext;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphqlTypeComparatorEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;
import tomaszewskij.przedPraca.egzaminy.DTO.PublicAppUserData;
import tomaszewskij.przedPraca.egzaminy.models.AppUser;
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


//    @PostMapping("/login")
//    public String login(@RequestBody String privateToken, HttpServletResponse response){
//
//        Cookie cookie = new Cookie("cookieName", "cookieValue");
//        cookie.setMaxAge(7 * 24 * 60 * 60);
//        cookie.setSecure(true);
//        cookie.setHttpOnly(true);
//        response.addCookie(cookie);
//
//
//        return privateToken;
//    }

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
    public List<AppUser> getAllAppUser() {
        return appUserService.getAllAppUsers();
    }
}
