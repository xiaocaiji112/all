package com.shop.controller;

import com.shop.config.RedisTemplate;
import com.shop.entity.Goods;
import com.shop.service.GoodsService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**http://localhost:8002/system/goods/test
 * (Goods)表控制层
 *
 * @author makejava
 * @since 2022-05-08 10:31:37
 */
@RestController
@RequestMapping("system/goods")
public class GoodsController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsService goodsService;
    @Resource
    RedisTemplate redisTemplete;

    @GetMapping("test")
    @PreAuthorize("hasAnyAuthority('test')")
    public String a(){
        redisTemplete.setObject("966","9999",5*60L);
        return "A";
    }
    /**
     * 分页查询
     *
     * @param
     * @param
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Goods>> queryByPage(Goods goods) {
        return ResponseEntity.ok(this.goodsService.queryByPage(goods, PageRequest.of(goods.getPage(),goods.getSize())));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Goods> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.goodsService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param goods 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Goods> add(Goods goods) {
        return ResponseEntity.ok(this.goodsService.insert(goods));
    }

    /**
     * 编辑数据
     *
     * @param goods 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Goods> edit(Goods goods) {
        return ResponseEntity.ok(this.goodsService.update(goods));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.goodsService.deleteById(id));
    }

}

