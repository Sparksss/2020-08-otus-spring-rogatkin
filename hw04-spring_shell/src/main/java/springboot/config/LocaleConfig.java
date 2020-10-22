package springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

/**
 * Created by ilya on Oct, 2020
 */
@ConfigurationProperties(prefix = "application")
public class LocaleConfig {
    private Locale locale;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
