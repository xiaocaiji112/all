package com.sshop.service;

import com.sshop.entity.Good;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Good)表服务接口
 *
 * @author makejava
 * @since 2022-05-24 15:40:44
 */
public interface GoodService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Good queryById(Integer id);

    /**
     * 分页查询
     *
     * @param good 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Good> queryByPage(Good good, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param good 实例对象
     * @return 实例对象
     */
    Good insert(Good good);

    /**
     * 修改数据
     *
     * @param good 实例对象
     * @return 实例对象
     */
    Good update(Good good);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
