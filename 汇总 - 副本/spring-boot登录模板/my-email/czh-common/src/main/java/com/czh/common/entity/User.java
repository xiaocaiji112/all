package com.czh.common.entity;

import com.czh.common.BaseUser;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2022-04-11 21:05:29
 */
public class User extends BaseUser implements Serializable {
    private static final long serialVersionUID = -89801780306528934L;
    private String uuid;
    private Integer userId;

    private String userName;
    private String code;
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date loginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creatTime;
    /**
     * 1.正在使用2.停用
     */
    private Integer status;
    
    private Integer delFlag;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", uuid='" + uuid + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", loginTime=" + loginTime +
                ", creatTime=" + creatTime +
                ", status=" + status +
                ", delFlag=" + delFlag +
                '}';
    }
}

