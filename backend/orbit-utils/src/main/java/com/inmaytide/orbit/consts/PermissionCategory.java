package com.inmaytide.orbit.consts;

public enum PermissionCategory {

    MENU("menu", 1),
    BUTTON("button", 2);

    private String name;
    private long code;

    private PermissionCategory(String name, long code) {
        this.name = name;
        this.code = code;
    }

    public String getName(long code) {
        for(PermissionCategory category : PermissionCategory.values()) {
            if (category.code == code) {
                return category.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public long getCode() {
        return code;
    }
}
