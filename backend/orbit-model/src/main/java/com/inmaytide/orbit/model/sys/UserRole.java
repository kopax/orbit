package com.inmaytide.orbit.model.sys;

import org.springframework.data.mybatis.annotations.Column;
import org.springframework.data.mybatis.annotations.Entity;
import org.springframework.data.mybatis.annotations.Id;

import java.io.Serializable;

@Entity(table = "sys_user_role")
public class UserRole implements Serializable {

    @Id
    private String id;
    @Column(name = "u_id")
    private String uId;
    @Column(name = "r_id")
    private String rId;

    public UserRole() {
    }

    public UserRole(String id, String uId, String rId) {
        this.id = id;
        this.uId = uId;
        this.rId = rId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }
}
