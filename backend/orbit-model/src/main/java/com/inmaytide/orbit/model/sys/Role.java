package com.inmaytide.orbit.model.sys;

import com.inmaytide.orbit.model.basic.BasicEntity;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mybatis.annotations.Entity;

import java.util.List;

@Entity(table = "sys_role")
public class Role extends BasicEntity {

    private static final long serialVersionUID = -8038307119098134671L;

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private String description;

    @Transient
    private List<Organization> organizations;

    @Transient
    private List<Permission> permissions;

    @Transient
    private List<User> users;

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

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
