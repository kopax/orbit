package com.inmaytide.webapp.model.sys;

import org.springframework.data.mybatis.annotations.Entity;
import org.springframework.data.mybatis.annotations.Id;

@Entity(table = "sys_user")
public class User implements java.io.Serializable {

    @Id
    private Long Id;
    private String username;
    private String name;
    private String password;

    public User() {
    }

    public User(Long id, String username, String name, String password) {
        Id = id;
        this.username = username;
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
