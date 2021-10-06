package com.spike.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.spike.crowd.entity.Admin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    Admin getAdminByLoginAcct(String loginAcct);

    PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

    Integer insertAdmin(Admin admin);

    void removeAdminById(List<Integer> adminId);

    Integer updateAdmin(Admin admin);

    List<Admin> getAll();

    Admin getAdminById(Integer adminId);
}
