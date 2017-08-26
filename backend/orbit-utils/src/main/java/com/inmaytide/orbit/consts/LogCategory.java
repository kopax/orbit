package com.inmaytide.orbit.consts;

public enum  LogCategory {

    SUCCED("succed", 1),
    FAILED("failed", 2);

    private String name;
    private Integer code;

    private LogCategory(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName(int code) {
        for(LogCategory category : LogCategory.values()) {
            if (category.code == code) {
                return category.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public Integer getCode() {
        return code;
    }
}
