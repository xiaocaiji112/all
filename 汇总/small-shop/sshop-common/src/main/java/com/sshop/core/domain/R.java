package com.sshop.core.domain;

import java.io.Serializable;
import java.util.Map;

public class R implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int status;
    private  Map<String ,Object> data;

    public static R success(){
        R r = new R();
        r.setStatus(200);
        return r;
    }
    public static R fail(){
        R r = new R();
        r.setStatus(404);
        return r;
    }

    public int getStatus() {
        return status;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public R setData(Map<String ,Object> msg) {
        this.data = msg;
        return this;
    }


    public R setStatus(int status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "R{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
