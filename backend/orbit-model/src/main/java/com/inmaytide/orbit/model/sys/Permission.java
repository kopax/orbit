package com.inmaytide.orbit.model.sys;

import com.inmaytide.orbit.model.basic.Basic;
import org.springframework.data.mybatis.annotations.Entity;
import org.springframework.data.mybatis.annotations.Id;

@Entity(table = "sys_permission")
public class Permission extends Basic {

    @Id
    private Long id;
    private String code;
    private String name;
    private String action;
    private String icon;
    private Long category;
    private String description;

    public Permission() {
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
