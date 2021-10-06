package com.spike.crowd.interceptor;

import com.google.gson.Gson;
import com.spike.crowd.entity.Admin;
import com.spike.crowd.util.CrowdConstant;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户访问后台资源时判断其登录状态
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        // 通过 request 对象获取 Session 对象，尝试从 Session 域中获取 Admin 对象
//        // 因为前后端分离采用 token 交互，当前实现不再适用
//        String jsonData = (String) request.getSession().getAttribute("admin");
//        if (jsonData == null || jsonData.length() == 0) {
//            response.sendRedirect(request.getContextPath() + "/index.html");
//            return false;
//        }

        return super.preHandle(request, response, handler);
    }
}
