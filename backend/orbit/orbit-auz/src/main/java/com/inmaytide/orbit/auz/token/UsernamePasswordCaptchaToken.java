package com.inmaytide.orbit.auz.token;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UsernamePasswordCaptchaToken extends UsernamePasswordToken {

    private String captcha;

    private String captchaKey;

    public UsernamePasswordCaptchaToken() {

    }

    public UsernamePasswordCaptchaToken(String username, String password, String captcha, String captchaKey) {
        super(username, password);
        this.captcha = captcha;
        this.captchaKey = captchaKey;
    }

    public UsernamePasswordCaptchaToken(String username, char[] password, boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
        this.captchaKey = captchaKey;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCaptchaKey() {
        return captchaKey;
    }

    public void setCaptchaKey(String captchaKey) {
        this.captchaKey = captchaKey;
    }
}
