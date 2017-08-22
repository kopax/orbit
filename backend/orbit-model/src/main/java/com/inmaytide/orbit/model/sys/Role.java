package com.inmaytide.orbit.model.sys;

import com.inmaytide.orbit.model.basic.BasicEntity;
import org.springframework.data.mybatis.annotations.Entity;

@Entity(table = "sys_role")
public class Role extends BasicEntity {

    private String code;
    private String name;
    private String description;

    public Role() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
