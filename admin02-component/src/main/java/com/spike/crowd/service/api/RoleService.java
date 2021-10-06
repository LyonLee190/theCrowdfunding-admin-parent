package com.spike.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.spike.crowd.entity.Role;

import java.util.List;

public interface RoleService {

    Role getRoleByName(String name);

    PageInfo<Role> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

    Integer insertRole(Role role);

    void removeRoleById(List<Integer> roleId);

    Integer updateRole(Role role);

    List<Role> getAll();

    Role getRoleById(Integer roleId);

    List<Integer> getRoleIdByAssignedAdminId(Integer adminId);

    void updateAdminRoles(Integer adminId, List<Integer> assignedRoles);
}
