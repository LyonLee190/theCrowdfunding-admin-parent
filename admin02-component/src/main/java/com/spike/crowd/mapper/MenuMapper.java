package com.spike.crowd.mapper;

import com.spike.crowd.entity.Menu;
import com.spike.crowd.entity.MenuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    int insert(Menu record);

    int insertSelective(Menu record);

    int deleteByExample(MenuExample example);

    int deleteByPrimaryKey(Integer id);

    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByPrimaryKeySelective(Menu record);

    List<Menu> selectByExample(MenuExample example);

    Menu selectByPrimaryKey(Integer id);

    int countByExample(MenuExample example);

    int updateByPrimaryKey(Menu record);
}