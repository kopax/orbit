package com.inmaytide.orbit.utils;

import com.inmaytide.orbit.consts.Constants;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class CommonUtils {

    public static String salt(String origin) {
        Assert.hasText(origin, "The parameter of salt method cannot be empty.");
        return StringUtils.arrayToDelimitedString(new String[]{origin, Constants.USER_PASSWORD_MD5_SALT}, "");
    }

    public static String md5(String origin, String salt) {
        Assert.hasText(origin, "The string to be encrypted can not be empty.");
        return new Md5Hash(origin, salt(salt)).toString();
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void disableResponseCache(HttpServletResponse response) {
        response.setDateHeader("Expires", 0L);
        response.addHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.addHeader("Pragma", "no-cache");
    }

    public static String generateCacheCaptchaKey(String v) {
        return new StringBuilder().append(Constants.CACHE_CAPTCHA_KEY)
                           .append("-").append(v).toString();
    }
}
