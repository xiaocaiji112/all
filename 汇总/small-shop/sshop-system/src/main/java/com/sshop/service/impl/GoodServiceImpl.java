package com.sshop.service.impl;

import com.sshop.entity.Good;
import com.sshop.dao.GoodDao;
import com.sshop.service.GoodService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Good)表服务实现类
 *
 * @author makejava
 * @since 2022-05-24 15:40:44
 */
@Service("goodService")
public class GoodServiceImpl implements GoodService {
    @Resource
    private GoodDao goodDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Good queryById(Integer id) {
        return this.goodDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param good 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Good> queryByPage(Good good, PageRequest pageRequest) {
        long total = this.goodDao.count(good);
        return new PageImpl<>(this.goodDao.queryAllByLimit(good, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param good 实例对象
     * @return 实例对象
     */
    @Override
    public Good insert(Good good) {
        this.goodDao.insert(good);
        return good;
    }

    /**
     * 修改数据
     *
     * @param good 实例对象
     * @return 实例对象
     */
    @Override
    public Good update(Good good) {
        this.goodDao.update(good);
        return this.queryById(good.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.goodDao.deleteById(id) > 0;
    }
}
