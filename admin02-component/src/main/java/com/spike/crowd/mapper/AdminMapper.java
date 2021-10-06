package com.spike.crowd.mapper;

import com.spike.crowd.entity.Admin;
import com.spike.crowd.entity.AdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    int insert(Admin record);

    int insertSelective(Admin record);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKey(Admin record);

    int updateByPrimaryKeySelective(Admin record);

    List<Admin> getAdminsByKeyword(String keyword);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int countByExample(AdminExample example);
}