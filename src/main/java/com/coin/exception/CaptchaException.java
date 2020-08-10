package com.coin.exception;

import javax.security.auth.login.LoginException;

/**
 * @ClassName CaptchaException
 * @Description: TODO
 * @Author kh
 * @Date 2020-08-10 15:19
 * @Version V1.0
 **/
public class CaptchaException extends LoginException {
    public CaptchaException() {
        super();
    }

    public CaptchaException(String msg) {
        super(msg);
    }
}
