package com.inmaytide.orbit.model.sys;

import com.inmaytide.orbit.model.basic.BasicEntity;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mybatis.annotations.Entity;

import java.util.List;

@Entity(table = "sys_permission")
public class Permission extends BasicEntity {

    private String code;
    private String name;
    private String action;
    private String icon;
    private Long category;
    private String description;
    private Long parent;
    private Integer sort;
    @Transient
    private List<Permission> children;

    public Permission() {
    }

    public static Permission of(Long id) {
        Permission permission = new Permission();
        permission.setId(id);
        return permission;
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

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }
}
