package com.example.oceanbioserver.controller;

import com.example.oceanbioserver.common.PageRequest;
import com.example.oceanbioserver.common.PageResponse;
import com.example.oceanbioserver.common.Result;
import com.example.oceanbioserver.entity.User;
import com.example.oceanbioserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param loginRequest 登录请求
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody User loginRequest) {
        String token = userService.login(loginRequest.getLoginName(), loginRequest.getPassword());
        return Result.success(token);
    }

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result<Boolean> register(@RequestBody User user) {
        boolean result = userService.register(user);
        return Result.success(result);
    }

    /**
     * 分页查询用户
     *
     * @param pageRequest 分页请求
     * @param loginName   登录名
     * @return 分页结果
     */
    @GetMapping("/list")
    public Result<PageResponse<User>> list(PageRequest pageRequest,
                                           @RequestParam(required = false) String loginName) {
        log.info("分页查询用户: page={}, size={}, loginName={}",
                pageRequest.getPage(), pageRequest.getSize(), loginName);
        PageResponse<User> pageResponse = userService.listUsersByPage(pageRequest, loginName);
        return Result.success(pageResponse);
    }

    /**
     * 根据ID查询用户
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public Result<User> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return Result.success(user);
    }

    /**
     * 创建用户
     *
     * @param user 用户信息
     * @return 创建结果
     */
    @PostMapping
    public Result<Boolean> createUser(@RequestBody User user) {
        boolean result = userService.createUser(user);
        return Result.success(result);
    }

    /**
     * 更新用户
     *
     * @param user 用户信息
     * @return 更新结果
     */
    @PutMapping
    public Result<Boolean> updateUser(@RequestBody User user) {
        boolean result = userService.updateUser(user);
        return Result.success(result);
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteUser(@PathVariable Long id) {
        boolean result = userService.deleteUser(id);
        return Result.success(result);
    }
}