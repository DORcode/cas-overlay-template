package com.coin.config;

import com.coin.config.handler.UsernamePasswordCaptchaAuthenticationHandler;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.principal.DefaultPrincipalFactory;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName CaptchaConfigurer
 * @Description: TODO
 * @Author kh
 * @Date 2020-08-07 14:30
 * @Version V1.0
 **/
@Configuration("UsernamePasswordCaptchaAuthConfig")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class CaptchaAuthConfiguration implements AuthenticationEventExecutionPlanConfigurer {

    @Autowired
    @Qualifier("servicesManager")
    ServicesManager servicesManager;

    @Bean
    public UsernamePasswordCaptchaAuthenticationHandler captchaAuthenticationHandler() {
        return new UsernamePasswordCaptchaAuthenticationHandler(UsernamePasswordCaptchaAuthenticationHandler.class.getName(), servicesManager,
                new DefaultPrincipalFactory(), 1);
    }
    @Override
    public void configureAuthenticationExecutionPlan(AuthenticationEventExecutionPlan plan) {
        plan.registerAuthenticationHandler(captchaAuthenticationHandler());
    }
}
