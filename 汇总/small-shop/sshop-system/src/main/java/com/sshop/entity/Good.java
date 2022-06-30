package com.sshop.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Good)实体类
 *
 * @author makejava
 * @since 2022-05-24 15:40:43
 */
public class Good extends BasicEntity implements Serializable {
    private static final long serialVersionUID = 692380476956800424L;
    
    private Integer id;
    
    private String name;
    
    private Double price;
    
    private String stock;
    
    private Integer userId;
    
    private Date publishTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

}

