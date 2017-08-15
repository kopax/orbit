package com.inmaytide.orbit.sys.model;

import com.inmaytide.orbit.sys.model.basic.Basic;
import org.springframework.data.mybatis.annotations.Entity;
import org.springframework.data.mybatis.annotations.Id;

@Entity(table = "sys_role")
public class Role extends Basic {

    @Id
    private Long id;
    private String code;
    private String name;
    private String description;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
