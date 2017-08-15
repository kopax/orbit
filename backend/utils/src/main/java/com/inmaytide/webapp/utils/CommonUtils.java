package com.inmaytide.webapp.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.UUID;

public class CommonUtils {

    private static final String USER_PASSWORD_MD5_SALT = "f391d46923fe39c1";

    public static String salt(String origin) {
        Assert.hasText(origin, "The parameter of salt method cannot be empty.");
        return StringUtils.arrayToDelimitedString(new String[]{origin, USER_PASSWORD_MD5_SALT}, "");
    }

    public static String md5(String origin, String salt) {
        Assert.hasText(origin, "The string to be encrypted can not be empty.");
        return new Md5Hash(origin, salt(salt)).toString();
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String... args) {
        System.out.println(md5("123456", "admin"));
    }

}
