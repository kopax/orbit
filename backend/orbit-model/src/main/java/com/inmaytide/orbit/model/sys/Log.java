package com.inmaytide.orbit.model.sys;

import com.inmaytide.orbit.office.excel.Comment;
import com.inmaytide.orbit.office.excel.ExcelTemplate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mybatis.annotations.Column;
import org.springframework.data.mybatis.annotations.Entity;
import org.springframework.data.mybatis.annotations.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(table = "sys_log")
public class Log implements Serializable{
    @Id
    private Long id;

    private Long operator;

    @Transient
    @Comment(column = 0, header = "操作人")
    private String operatorName;

    @Column(name = "log_time")
    @Comment(column = 1, header = "操作时间")
    private LocalDateTime logTime;

    @Comment(column = 2, header = "日志内容")
    private String content;

    @Comment(column = 3, header = "日志详情")
    private String details;

    public static Log of() {
        Log log = new Log();
        log.setLogTime(LocalDateTime.now());
        return log;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public LocalDateTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}