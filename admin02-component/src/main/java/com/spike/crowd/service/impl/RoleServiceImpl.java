package com.spike.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spike.crowd.entity.MenuExample;
import com.spike.crowd.entity.Role;
import com.spike.crowd.entity.RoleExample;
import com.spike.crowd.mapper.AuthMapper;
import com.spike.crowd.mapper.RoleMapper;
import com.spike.crowd.service.api.RoleService;
import com.spike.crowd.util.CrowdConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AuthMapper authMapper;

    @Override
    public Role getRoleByName(String name) {

        // 根据名称查询 Role 对象
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andNameEqualTo(name);
        List<Role> roles = roleMapper.selectByExample(roleExample);

        if (roles == null || roles.size() != 1) {
            throw new RuntimeException(CrowdConstant.MESSAGE_QUERY_ROLE_FAILED);
        }
        Role role = roles.get(0);
        if (role == null) {
            throw new RuntimeException(CrowdConstant.MESSAGE_QUERY_ROLE_FAILED);
        }

        return role;
    }

    @Override
    public PageInfo<Role> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        // 1.开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        // 2.查询 Role 数据
        List<Role> rolesByKeyword = roleMapper.getRolesByKeyword(keyword);
        // 3.为了方便页面使用将 roleList 封装为 PageInfo
        return new PageInfo<>(rolesByKeyword);
    }

    @Override
    public Integer insertRole(Role role) {

        Integer count = null;
        // 执行保存
        try {
            roleMapper.insert(role);
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("角色已存在");
        }
        return count;
    }

    @Override
    public void removeRoleById(List<Integer> roleId) {
        roleId.forEach(roleMapper::deleteAdminRoleByRoleId);
        roleId.forEach(authMapper::deleteRoleAuthByRoleId);
        roleId.forEach(roleMapper::deleteByPrimaryKey);

    }

    @Override
    public Integer updateRole(Role role) {

        Integer count = null;
        // 执行保存
        try {
            roleMapper.updateByPrimaryKeySelective(role);
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("角色已存在");
        }
        return count;
    }

    @Override
    public List<Role> getAll() {
        RoleExample roleExample = new RoleExample();
        roleExample.setOrderByClause("id");
        return roleMapper.selectByExample(roleExample);
    }

    @Override
    public Role getRoleById(Integer roleId) {
        return null;
    }

    @Override
    public List<Integer> getRoleIdByAssignedAdminId(Integer adminId) {
        return roleMapper.selectByAssignedAdminId(adminId);
    }

    @Override
    public void updateAdminRoles(Integer adminId, List<Integer> adminRoles) {
        // 删除该用户的所有权限记录后重新插入
        roleMapper.deleteAdminRoleByAdminId(adminId);
        adminRoles.forEach(roleId -> roleMapper.insertAdminRole(adminId, roleId));
    }
}
