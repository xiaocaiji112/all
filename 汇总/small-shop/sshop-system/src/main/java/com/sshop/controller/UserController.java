package com.sshop.controller;

import com.sshop.core.domain.R;
import com.sshop.entity.BuyForm;
import com.sshop.entity.User;
import com.sshop.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2022-05-24 16:25:50
 */
@RestController
@RequestMapping("system/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<User>> queryByPage(User user) {
        return ResponseEntity.ok(this.userService.queryByPage(user, PageRequest.of(user.getPage(), user.getSize())));
    }

    @PostMapping("buysingle")
    public R butSingle(@RequestBody(required = false) BuyForm form){
        BuyForm form1 = new BuyForm("44","4","4",5,"4","4",10,100.5,new Date(),"1","1");
        userService.handleForm(form1);
        return R.success();
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<User> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.userService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<User> add(User user) {
        return ResponseEntity.ok(this.userService.insert(user));
    }

    /**
     * 编辑数据
     *
     * @param user 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<User> edit(User user) {
        return ResponseEntity.ok(this.userService.update(user));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.userService.deleteById(id));
    }

}

