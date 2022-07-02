package com.sshop.entity;

import java.io.Serializable;
import java.util.Date;

public class BuyForm implements Serializable {
    private static final long serialVersionUID = 692380476956800424L;
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

    public BuyForm(String recipientname, String recipientaddress, String payment, Integer goodid, String goodnums, String explain, Integer userid, Double price, Date date, String status, String id) {
        this.recipientname = recipientname;
        this.recipientaddress = recipientaddress;
        this.payment = payment;
        this.goodid = goodid;
        this.goodnums = goodnums;
        this.explain_order = explain;
        this.userid = userid;
        this.price = price;
        this.date = date;
        this.status_order = status;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status_order;
    }

    public void setStatus(String status) {
        this.status_order = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

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

    public String getExplain_order() {
        return explain_order;
    }

    public void setExplain_order(String explain_order) {
        this.explain_order = explain_order;
    }
}
