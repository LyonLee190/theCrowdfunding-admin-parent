package com.spike.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spike.crowd.entity.Admin;
import com.spike.crowd.entity.AdminExample;
import com.spike.crowd.mapper.AdminMapper;
import com.spike.crowd.mapper.RoleMapper;
import com.spike.crowd.service.api.AdminService;
import com.spike.crowd.util.CrowdConstant;
import com.spike.crowd.util.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Admin getAdminByLoginAcct(String loginAcct) {

        // 根据登录账号查询 Admin 对象
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> admins = adminMapper.selectByExample(adminExample);

        if (admins == null || admins.size() != 1) {
            throw new RuntimeException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        Admin admin = admins.get(0);
        if (admin == null) {
            throw new RuntimeException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        return admin;
    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        // 1.开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        // 2.查询 Admin 数据
        List<Admin> adminsByKeyword = adminMapper.getAdminsByKeyword(keyword);
        // 3.为了方便页面使用将 adminList 封装为 PageInfo
        return new PageInfo<>(adminsByKeyword);
    }

    @Override
    public Integer insertAdmin(Admin admin) {

        // 针对登录密码进行加密
        admin.setUserPswd(Encryption.encrypt(admin.getUserPswd()));

        // 生成当前系统时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YY-MM-DD HH:mm:ss");
        String currentTime = simpleDateFormat.format(new Date());
        admin.setCreateTime(currentTime);

        Integer count = null;
        // 执行保存
        try {
            adminMapper.insert(admin);
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("用户名已存在");
        }
        return count;
    }

    @Override
    public void removeAdminById(List<Integer> adminId) {
        adminId.forEach(roleMapper::deleteAdminRoleByAdminId);
        adminId.forEach(adminMapper::deleteByPrimaryKey);
    }

    @Override
    public Integer updateAdmin(Admin admin) {

        // 针对登录密码进行加密
        admin.setUserPswd(Encryption.encrypt(admin.getUserPswd()));

        Integer count = null;
        // 执行保存
        try {
            adminMapper.updateByPrimaryKeySelective(admin);
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("用户名已存在");
        }
        return count;
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin getAdminById(Integer adminId) {
        return null;
    }
}
