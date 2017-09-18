package com.inmaytide.orbit.model.sys;

import org.springframework.data.mybatis.annotations.Column;
import org.springframework.data.mybatis.annotations.Entity;
import org.springframework.data.mybatis.annotations.Id;

import java.time.LocalDateTime;

@Entity(table = "sys_log")
public class Log implements java.io.Serializable {

    private static final long serialVersionUID = 9133989827160716863L;

    @Id(strategy = Id.GenerationType.ASSIGNATION)
    private Long id;

    private String name;

    private Long operator;

    @Column(name = "class_name")
    private String className;

    @Column(name = "method_name")
    private String methodName;

    private LocalDateTime time;

    @Column(name = "is_succeed")
    private String isSucceed;

    private String message;

    public Log() {
        this.time = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getIsSucceed() {
        return isSucceed;
    }

    public void setIsSucceed(String isSucceed) {
        this.isSucceed = isSucceed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}