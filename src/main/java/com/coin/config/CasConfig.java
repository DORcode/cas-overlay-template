package com.coin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;

/**
 * @ClassName CasConfig
 * @Description: TODO
 * @Author kh
 * @Date 2020-07-29 19:57
 * @Version V1.0
 **/
@Configuration
@ComponentScan("com.coin")
public class CasConfig {

    @Bean
    public CookieLocaleResolver cookieLocaleResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        Locale locale = new Locale("zh_CN");
        resolver.setDefaultLocale(locale);
        return resolver;
    }
}
