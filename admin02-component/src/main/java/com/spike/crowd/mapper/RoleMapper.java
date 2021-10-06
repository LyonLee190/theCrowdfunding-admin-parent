package com.spike.crowd.mapper;

import com.spike.crowd.entity.Admin;
import com.spike.crowd.entity.Role;
import com.spike.crowd.entity.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKey(Role record);

    int updateByPrimaryKeySelective(Role record);

    List<Role> getRolesByKeyword(String keyword);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int countByExample(RoleExample example);

    List<Integer> selectByAssignedAdminId(Integer AdminId);

    int insertAdminRole(@Param("adminId") Integer adminId, @Param("roleId") Integer roleId);

    int deleteAdminRoleByAdminId(Integer adminId);

    int deleteAdminRoleByRoleId(Integer roleId);
}