package com.shap.entity;

import java.io.Serializable;

/**
 * (Msg)实体类
 *
 * @author makejava
 * @since 2022-06-09 12:41:28
 */
public class Msg implements Serializable {
    private static final long serialVersionUID = -26862183517365477L;


    private String from;
    
    private String to;
    
    private String msg;
    
    private Integer count;
    public Msg(String from,String to,String msg ,int count){
       this.from = from;
       this.to = to;
       this.msg = msg;
       this.count = count;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}

