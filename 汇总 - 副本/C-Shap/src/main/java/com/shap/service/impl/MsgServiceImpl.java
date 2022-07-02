package com.shap.service.impl;

import com.shap.dao.MsgCDao;
import com.shap.entity.Msg;
import com.shap.dao.MsgDao;
import com.shap.entity.MsgC;
import com.shap.service.MsgService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * (Msg)表服务实现类
 *
 * @author makejava
 * @since 2022-06-09 12:41:29
 */
@Service("msgService")
public class MsgServiceImpl implements MsgService {
    @Resource
    private MsgDao msgDao;

    @Resource
    private MsgCDao msgCDao;
    @Override
    public void save(String from, String to,String msg, int count) {
        Msg message = new Msg(from,to,msg,count);
        msgDao.insert(message);
    }

    @Override
    public boolean check(String from, String to,int current) {
        List<Msg> msg = msgDao.getMsg(from, to);
        if(msg.size() > current){

            return true;
        }
        return false;
    }

    @Override
    public MsgC getCurrent(String from,String to) {
        MsgC current = msgCDao.getCurrent(from, to);
        return current;
    }

    @Override
    public void upDate(String from, String to, String count) {
        MsgC msgC = new MsgC();
        msgC.setCurrentConout(count);
        msgC.setFrom(from);
        msgC.setTo(to);
        msgCDao.update(msgC);
    }

    @Override
    public List<Msg> getMsg(String from, String to) {
        return   msgDao.getMsg(from,to);

    }

    @Override
    public List<Msg> getMsgByIndex(String from, String to, String current) {
        return msgDao.getMsgByIndex(from,to,current);
    }

}
