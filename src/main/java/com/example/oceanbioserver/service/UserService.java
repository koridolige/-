package com.example.oceanbioserver.service;

import com.example.oceanbioserver.common.PageRequest;
import com.example.oceanbioserver.common.PageResponse;
import com.example.oceanbioserver.entity.User;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 用户登录
     * @param loginName 登录名
     * @param password 密码
     * @return 用户信息
     */
    String login(String loginName, String password);

    /**
     * 用户注册
     * @param user 用户信息
     * @return 是否成功
     */
    boolean register(User user);

    /**
     * 获取用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    User getUser(Long id);

    /**
     * 分页查询用户列表
     * @param pageRequest 分页请求
     * @param loginName 登录名
     * @return 用户列表
     */
    PageResponse<User> listUsersByPage(PageRequest pageRequest, String loginName);

    /**
     * 创建用户
     * @param user 用户信息
     * @return 是否成功
     */
    boolean createUser(User user);

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return 是否成功
     */
    boolean updateUser(User user);

    /**
     * 删除用户
     * @param id 用户ID
     * @return 是否成功
     */
    boolean deleteUser(Long id);
}
