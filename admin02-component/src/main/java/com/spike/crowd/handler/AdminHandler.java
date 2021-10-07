package com.spike.crowd.handler;

import com.github.pagehelper.PageInfo;
import com.spike.crowd.entity.Admin;
import com.spike.crowd.service.api.AdminService;
import com.spike.crowd.util.CrowdConstant;
import com.spike.crowd.util.Encryption;
import com.spike.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@RestController
@CrossOrigin
public class AdminHandler {

    @Autowired
    private AdminService adminService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("admin/login.json")
    public ResultEntity<Admin> login(HttpSession httpSession, String loginAcct, String userPswd) throws NoSuchAlgorithmException {

        Admin adminByLoginAcct = adminService.getAdminByLoginAcct(loginAcct);
        if (!Encryption.encrypt(userPswd).equals(adminByLoginAcct.getUserPswd())) {
            throw new RuntimeException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        logger.info(httpSession.getId());
        httpSession.setAttribute("admin", adminByLoginAcct);

        return ResultEntity.successWithData(adminByLoginAcct);
    }

    @RequestMapping("admin/get/admin-page.json")
    public ResultEntity<PageInfo<Admin>> page(
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        PageInfo<Admin> admins = adminService.getPageInfo(keyword, pageNum, pageSize);
        return ResultEntity.successWithData(admins);
    }

    @RequestMapping("admin/insert/admin.json")
    public ResultEntity<String> insert(Admin admin) {

        admin.setId(null);
        adminService.insertAdmin(admin);
        return ResultEntity.successWithoutData();
    }

    @RequestMapping("admin/update/admin.json")
    public ResultEntity<String> update(Admin admin) {

        adminService.updateAdmin(admin);
        return ResultEntity.successWithoutData();
    }

    @RequestMapping("admin/remove/admin.json")
    public ResultEntity<String> remove(@RequestBody Integer[] checkedAdminId) {

        adminService.removeAdminById(Arrays.asList(checkedAdminId));
        return ResultEntity.successWithoutData();
    }
}
