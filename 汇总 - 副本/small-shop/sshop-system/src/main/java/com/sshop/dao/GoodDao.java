package com.sshop.dao;

import com.sshop.entity.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Good)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-24 15:40:42
 */
@Mapper
public interface GoodDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Good queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param good 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Good> queryAllByLimit(@Param("good") Good good, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param good 查询条件
     * @return 总行数
     */
    long count(Good good);

    /**
     * 新增数据
     *
     * @param good 实例对象
     * @return 影响行数
     */
    int insert(Good good);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Good> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Good> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Good> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Good> entities);

    /**
     * 修改数据
     *
     * @param good 实例对象
     * @return 影响行数
     */
    int update(Good good);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

