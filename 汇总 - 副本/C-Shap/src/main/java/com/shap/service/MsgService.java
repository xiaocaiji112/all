package com.shap.service;

import com.shap.entity.Msg;
import com.shap.entity.MsgC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Msg)表服务接口
 *
 * @author makejava
 * @since 2022-06-09 12:41:28
 */
public interface MsgService {


    void save(String from,String to ,String msg,int count);


    boolean check(String from, String to,int current);

    MsgC getCurrent(String from, String to);
    void upDate(String from,String to,String count);
    List<Msg> getMsg(String from,String to);

    List<Msg> getMsgByIndex(String from, String to, String current);
}
