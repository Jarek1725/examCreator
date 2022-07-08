package tomaszewskij.przedPraca.egzaminy;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**").allowedOrigins("https://examcreator.herokuapp.com").allowedMethods("*").allowedHeaders("*")
                .exposedHeaders("*").allowCredentials(true).maxAge(3600);
    }

}
