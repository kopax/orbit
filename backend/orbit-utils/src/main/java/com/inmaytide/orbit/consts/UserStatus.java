package com.inmaytide.orbit.consts;

public enum UserStatus {

    NORMAL("Normal", 1L),
    LOCKED("Locked", 2L);

    private String name;
    private Long code;

    private UserStatus(String name, Long code) {
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

    public String getName() {
        return name;
    }

    public Long getCode() {
        return code;
    }
}
