package com.sshop.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (OrderForm)实体类
 *
 * @author makejava
 * @since 2022-05-24 18:04:15
 */
public class OrderForm implements Serializable {
    private static final long serialVersionUID = 911982371846908522L;
    
    private String recipientname;
    
    private String recipientaddress;
    
    private String payment;
    
    private Integer goodid;
    
    private String goodnums;
    
    private String explain_order;
    
    private Integer userid;
    
    private Double price;
    
    private Date date;
    
    private String status_order;
    
    private String id;


    public String getRecipientname() {
        return recipientname;
    }

    public void setRecipientname(String recipientname) {
        this.recipientname = recipientname;
    }

    public String getRecipientaddress() {
        return recipientaddress;
    }

    public void setRecipientaddress(String recipientaddress) {
        this.recipientaddress = recipientaddress;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Integer getGoodid() {
        return goodid;
    }

    public void setGoodid(Integer goodid) {
        this.goodid = goodid;
    }

    public String getGoodnums() {
        return goodnums;
    }

    public void setGoodnums(String goodnums) {
        this.goodnums = goodnums;
    }

    public String getExplain() {
        return explain_order;
    }

    public void setExplain(String explain) {
        this.explain_order = explain;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status_order;
    }

    public void setStatus(String status) {
        this.status_order = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

