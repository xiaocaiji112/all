package com.example.handler;

import com.alibaba.fastjson.JSON;
import com.example.entity.MyResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        MyResult result = new MyResult(555,"授权失败");

        //授权失败处理异常
        renderString(response, JSON.toJSONString(result));
    }
    public static void renderString(HttpServletResponse response,String msg){
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().print(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
