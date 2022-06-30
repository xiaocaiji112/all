package com.shap.dao;

import com.shap.entity.Msg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Msg)表数据库访问层
 *
 * @author makejava
 * @since 2022-06-09 12:41:27
 */
@Mapper
public interface MsgDao {

    int insert(Msg msg);

    List<Msg> getMsg(String from, String to);

    List<Msg> getMsgByIndex(String from, String to, String current);
}

