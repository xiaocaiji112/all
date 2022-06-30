package com.shap.controller;

import com.shap.entity.Msg;
import com.shap.entity.MsgC;
import com.shap.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController

public class TestController {


    @Autowired
    MsgService msgService;

    @GetMapping("test")
    public String t(){
        return "成功";
    }
   @PostMapping("send")
   public List<String> send(String from,String to,String msg){
       List<Msg> msg1 = msgService.getMsg(from, to);
       msgService.save(from,to,msg,msg1.size()+1);
       List<String > list = new ArrayList<>();
       list.add("status");
       list.add("1");
       return list;
   }
    @PostMapping("login")
    public List<String > Get2(String username, String password, HttpServletRequest httpServletRequest){
        System.out.println("username = " + username);
        List<String> list = new ArrayList<>();
        list.add("status");

        if(password .equals("123") ){
            list.add("1");
        }else {
            list.add("0");
        }
        return list;
    }
    @PostMapping("getCurrent")
    public List<String> getCurrent(String from,String to){
        MsgC current = msgService.getCurrent(from, to);

        List<String> list = new ArrayList<>();
        System.out.println("current = " + current.getCurrentConout());
        list.add("current");
        list.add(current.getCurrentConout());
       return list;
    }
    @PostMapping("check")
    public List<String> check( String from, String to, Integer current){
        System.out.println("check" + from +" ====" + to);
        List<Msg> msg = msgService.getMsg(from, to);
        List<String> list = new ArrayList<>();
        list.add("status");
        System.out.println("msg.size() = " + msg.size());
        System.out.println("current = " + current);
        System.out.println(msg.size() > current);
        if(msg.size() > current){
            list.add("1");
            msgService.upDate(from,to,String.valueOf(msg.size()));
        }else {
            list.add("0");
        }
          return list;
    }
    @PostMapping("getmsg")
    public List<String> getmsg(String from,String to,String current){
        List<Msg> msgByIndex = msgService.getMsgByIndex(from, to, current);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < msgByIndex.size(); i++) {
            list.add(String.valueOf(i));
            list.add(msgByIndex.get(i).getMsg());
        }
        System.out.println("======================");
        System.out.println(list);
        return list;
    }
}
