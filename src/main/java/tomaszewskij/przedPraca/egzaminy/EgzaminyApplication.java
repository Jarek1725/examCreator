package tomaszewskij.przedPraca.egzaminy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DatabaseProps.class)
public class EgzaminyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EgzaminyApplication.class, args);
	}

}
