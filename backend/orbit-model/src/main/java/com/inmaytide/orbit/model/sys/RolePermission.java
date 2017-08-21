package com.inmaytide.orbit.model.sys;

import org.springframework.data.mybatis.annotations.Column;
import org.springframework.data.mybatis.annotations.Entity;
import org.springframework.data.mybatis.annotations.Id;

import java.io.Serializable;

@Entity(table = "sys_role_permission")
public class RolePermission implements Serializable {

    @Id
    private String id;
    @Column(name = "r_id")
    private String rId;
    @Column(name = "p_id")
    private String pId;

    public RolePermission() {
    }

    public RolePermission(String id, String rId, String pId) {
        this.id = id;
        this.rId = rId;
        this.pId = pId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }
}
