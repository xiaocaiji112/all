package com.shop.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2022-05-08 10:31:36
 */
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 736070069435135750L;
    
    private Integer userId;
    
    private String userName;
    
    private String userRole;
    
    private Integer userShopNums;
    
    private String password;
    
    private Date createTime;
    
    private Date updateTime;
    /**
     * 0.停用1.启用
     */
    private String userStatus;
    
    private String userProfile;
    
    private Integer userShopCarId;


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

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Integer getUserShopNums() {
        return userShopNums;
    }

    public void setUserShopNums(Integer userShopNums) {
        this.userShopNums = userShopNums;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public Integer getUserShopCarId() {
        return userShopCarId;
    }

    public void setUserShopCarId(Integer userShopCarId) {
        this.userShopCarId = userShopCarId;
    }

}

