package tomaszewskij.przedPraca.egzaminy;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("dbprops")
public record DatabaseProps(String db_url, String db_login, String db_props) {

}
