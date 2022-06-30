package com.shap.dao;

import com.shap.entity.MsgC;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (MsgC)表数据库访问层
 *
 * @author makejava
 * @since 2022-06-09 12:41:29
 */
@Mapper
public interface MsgCDao {



    int update(MsgC msgC);


    MsgC getCurrent(String from, String to);

}

