package com.spike.crowd.mapper;

import com.spike.crowd.entity.Auth;
import com.spike.crowd.entity.AuthExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthMapper {
    int insert(Auth record);

    int insertSelective(Auth record);

    int deleteByExample(AuthExample example);

    int deleteByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    List<Auth> selectByExample(AuthExample example);

    Auth selectByPrimaryKey(Integer id);

    int countByExample(AuthExample example);

    List<Integer> selectByAssignedRoleId(Integer roleId);

    List<Integer> selectByAssignedMenuId(Integer menuId);

    int insertRoleAuth(@Param("roleId") Integer roleId, @Param("authId") Integer authId);

    int insertMenuAuth(@Param("menuId") Integer menuId, @Param("authId") Integer authId);

    int deleteRoleAuthByRoleId(Integer roleId);

    int deleteRoleAuthByAuthId(Integer authId);

    int deleteMenuAuthByMenuId(Integer menuId);

    int deleteMenuAuthByAuthId(Integer authId);
}