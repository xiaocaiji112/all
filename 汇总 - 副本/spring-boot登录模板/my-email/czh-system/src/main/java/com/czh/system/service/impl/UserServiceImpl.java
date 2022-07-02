package com.czh.system.service.impl;

import com.czh.common.entity.LoginUser;
import com.czh.common.entity.User;
import com.czh.common.exception.loginexception.CodeErrorException;
import com.czh.common.exception.loginexception.PasswordErrorException;
import com.czh.common.exception.loginexception.UserNotFoundException;
import com.czh.system.dao.UserDao;
import com.czh.system.service.UserService;
import com.czh.system.web.service.CaptchaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-04-11 21:05:30
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private  RestTemplate restTemplate;

    @Resource
    private CaptchaService captchaService;

    private final ObjectMapper objectMapper  = new ObjectMapper();
    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer userId) {
        return this.userDao.queryById(userId);
    }

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<User> queryByPage(User user, PageRequest pageRequest) {
        long total = this.userDao.count(user);
        return new PageImpl<>(this.userDao.queryAllByLimit(user, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer userId) {
        return this.userDao.deleteById(userId) > 0;
    }

    @Override
    public LoginUser login(User user, HttpServletRequest request) {
           if(!captchaService.verifyCode(user.getUuid(),user.getCode())){
               throw new CodeErrorException("验证码错误");
           }
        User user1 = queryByName(user.getUserName());
        if(user1 == null){
      throw new UserNotFoundException("用户不存在");
        }else if(!Objects.equals(user.getPassword(), user1.getPassword())){
            throw new PasswordErrorException("密码错了");
        }else {
            ResponseEntity<String> forEntity = restTemplate.getForEntity("https://whois.pconline.com.cn/ipJson.jsp?ip=" + request.getRemoteAddr() + "&json=true",
                    String.class);
            String body = forEntity.getBody();
            Map<String ,String> map = null;
            try {
                map =  objectMapper.
                        readValue(body, new TypeReference<>() {
                        });
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            UserAgent userAgent = new UserAgent(request.getHeader("User-Agent"));

            System.out.println(user1);

            String data = map.get("addr")+map.get("pro")+map.get("city");
            LoginUser loginUser = new LoginUser();
            loginUser.setUserId(user1.getUserId());
            loginUser.setUserName(user1.getUserName());
            loginUser.setIp(request.getRemoteAddr());
            loginUser.setAddress(data);
            loginUser.setOs(userAgent.getOperatingSystem().getName());
            loginUser.setBrowser(userAgent.getBrowser().getName());
            return loginUser;
        }
    }

    private User queryByName(String userName) {
        return userDao.queryByName(userName);
    }
}
