package com.sshop.controller;

import com.sshop.entity.Good;
import com.sshop.service.GoodService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Good)表控制层
 *
 * @author makejava
 * @since 2022-05-24 15:40:42
 */
@RestController
@RequestMapping("system/goods")
public class GoodController {
    /**
     * 服务对象
     */
    @Resource
    private GoodService goodService;

    /**
     * 分页查询
     *
     * @param good 筛选条件
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Good>> queryByPage(Good good) {
        System.out.println(good.getSize());
        System.out.println(good.getPage());
        return ResponseEntity.ok(this.goodService.queryByPage(good, PageRequest.of(good.getPage(),good.getSize())));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Good> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.goodService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param good 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Good> add(Good good) {
        return ResponseEntity.ok(this.goodService.insert(good));
    }

    /**
     * 编辑数据
     *
     * @param good 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Good> edit(Good good) {
        return ResponseEntity.ok(this.goodService.update(good));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.goodService.deleteById(id));
    }

}

