package com.coin.config.handler;

import com.coin.RememberMeUsernamePasswordCaptchaCredential;
import com.coin.exception.CaptchaException;
import lombok.extern.slf4j.Slf4j;
import org.apereo.cas.authentication.*;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.authentication.principal.PrincipalResolver;
import org.apereo.cas.services.JpaServiceRegistry;
import org.apereo.cas.services.RegexRegisteredService;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.webflow.action.AbstractAction;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;
import java.security.GeneralSecurityException;
import java.util.*;

/**
 * @ClassName CapchaProcessingAuthenticationHandler
 * @Description: TODO
 * @Author kh
 * @Date 2020-08-07 10:44
 * @Version V1.0
 **/
@Slf4j
public class UsernamePasswordCaptchaAuthenticationHandler extends AbstractPreAndPostProcessingAuthenticationHandler {

    // service

    public UsernamePasswordCaptchaAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    @Override
    protected AuthenticationHandlerExecutionResult doAuthentication(Credential credential) throws GeneralSecurityException {
        RememberMeUsernamePasswordCaptchaCredential rmpc = (RememberMeUsernamePasswordCaptchaCredential) credential;

        String captcha = rmpc.getCaptcha();
        String username = rmpc.getUsername();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession(false);
        if(session == null) {
            session = requestAttributes.getRequest().getSession();
        }
        String captchaOrigin = null;
        Object object = session.getAttribute("captcha");
        if(object != null) {
            captchaOrigin = (String) session.getAttribute("captcha");
            long captchaValidTime = (long) session.getAttribute("captchaValidTime");

            if (System.currentTimeMillis() > captchaValidTime) {
                throw new CaptchaException("the captcha is invalid");
            }
            if (StringUtils.isEmpty(captcha) || !captcha.toLowerCase().equals(captchaOrigin.toLowerCase())) {
                throw new CaptchaException("the captcha input incorrect");
            }
        }

        if (!rmpc.getPassword().equals("user")) {
            throw new FailedLoginException("帐户或密码错误");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("username", rmpc.getUsername());
        map.put("id", 1);
        map.put("mobile", "18620121232");
        List<MessageDescriptor> warning = new ArrayList<MessageDescriptor>();
        return createHandlerResult(credential, principalFactory.createPrincipal(username, map), warning);
    }

    @Override
    public boolean supports(Credential credential) {
        return credential instanceof RememberMeUsernamePasswordCaptchaCredential;
    }
}
