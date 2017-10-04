package com.inmaytide.orbit.consts;

import java.util.Arrays;

public enum UserStatus {

    NORMAL(1L, "Normal"),
    LOCKED(2L, "Locked");

    private String name;
    private Long code;

    private UserStatus(Long code, String name) {
        this.name = name;
        this.code = code;
    }

    public String getName(long code) {
        for(UserStatus status : UserStatus.values()) {
            if (status.code == code) {
                return status.name;
            }
        }
        return null;
    }

    public static UserStatus valueOf(Long code) {
        return Arrays.stream(values())
                .filter(userStatus -> userStatus.getCode().equals(code))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public String getName() {
        return name;
    }

    public Long getCode() {
        return code;
    }
}
