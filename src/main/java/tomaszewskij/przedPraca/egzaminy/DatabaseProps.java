package tomaszewskij.przedPraca.egzaminy;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("dbprops")
public record DatabaseProps(String url, String login, String pass) {

}
