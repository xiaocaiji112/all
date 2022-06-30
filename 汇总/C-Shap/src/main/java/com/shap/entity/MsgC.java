package com.shap.entity;

import java.io.Serializable;

/**
 * (MsgC)实体类
 *
 * @author makejava
 * @since 2022-06-09 12:41:29
 */
public class MsgC implements Serializable {
    private static final long serialVersionUID = 179638325241243817L;
    
    private String from;
    
    private String to;
    
    private String currentConout;


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

    public String getCurrentConout() {
        return currentConout;
    }

    public void setCurrentConout(String currentConout) {
        this.currentConout = currentConout;
    }

}

