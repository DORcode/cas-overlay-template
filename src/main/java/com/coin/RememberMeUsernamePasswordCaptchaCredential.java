package com.coin;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apereo.cas.authentication.RememberMeUsernamePasswordCredential;

/**
 * @ClassName RememberMeUsernamePasswordCaptchaCredential
 * @Description: TODO
 * @Author kh
 * @Date 2020-08-07 11:07
 * @Version V1.0
 **/
@Slf4j
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RememberMeUsernamePasswordCaptchaCredential extends RememberMeUsernamePasswordCredential {
    private String captcha;
}
