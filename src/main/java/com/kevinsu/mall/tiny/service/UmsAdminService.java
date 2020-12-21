package com.kevinsu.mall.tiny.service;

import com.kevinsu.mall.tiny.mbg.model.UmsAdmin;
import com.kevinsu.mall.tiny.mbg.model.UmsPermission;

import java.util.List;

/**
 *  后台管理员Service
 * @author SuGengTian kevinsu917@126.com
 * @since 2020-12-18-18:41
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdmin umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<UmsPermission> getPermissionList(Long adminId);

    /**
     * 初始化用户密码
     *
     * @param username 用户名
     * @param password 用户密码，如果没有填则使用123456
     * @return
     */
    UmsAdmin passwordInit(String username, String password);
}