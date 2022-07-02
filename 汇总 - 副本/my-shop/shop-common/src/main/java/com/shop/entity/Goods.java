package com.shop.entity;

import java.io.Serializable;

/**
 * (Goods)实体类
 *
 * @author makejava
 * @since 2022-05-08 10:31:37
 */
public class Goods extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -96737009014989177L;
    
    private Integer goodsId;
    
    private String goodsName;
    
    private Integer userId;
    
    private Integer goodStock;


    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodStock() {
        return goodStock;
    }

    public void setGoodStock(Integer goodStock) {
        this.goodStock = goodStock;
    }

}

