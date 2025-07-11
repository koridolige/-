package com.example.oceanbioserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.oceanbioserver.common.BusinessException;
import com.example.oceanbioserver.common.PageRequest;
import com.example.oceanbioserver.common.PageResponse;
import com.example.oceanbioserver.entity.User;
import com.example.oceanbioserver.service.UserService;
import com.example.oceanbioserver.mapper.UserMapper;
import com.example.oceanbioserver.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 用户服务实现类
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String login(String loginName, String password) {
        // 查询用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getLoginName, loginName);
        User user = userMapper.selectOne(queryWrapper);

        // 用户不存在
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        // 密码错误
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BusinessException("密码错误");
        }

        // 生成token
        return jwtUtil.generateToken(user);
    }

    @Override
    public boolean register(User user) {
        // 检查登录名是否已存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getLoginName, user.getLoginName());
        if (userMapper.selectCount(queryWrapper) > 0) {
            throw new BusinessException("登录名已存在");
        }

        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.insert(user) > 0;
    }

    @Override
    public User getUser(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public PageResponse<User> listUsersByPage(PageRequest pageRequest, String loginName) {
        Page<User> page = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        // 添加查询条件
        if (StringUtils.hasText(loginName)) {
            queryWrapper.like(User::getLoginName, loginName);
        }

        // 排序
        queryWrapper.orderByDesc(User::getId);

        // 记录查询条件
        log.info("查询用户列表，条件: loginName={}", loginName);

        Page<User> userPage = userMapper.selectPage(page, queryWrapper);

        // 记录查询结果
        log.info("查询用户列表，总数: {}", userPage.getTotal());

        return new PageResponse<>(userPage.getRecords(), userPage.getTotal());
    }

    @Override
    public boolean createUser(User user) {
        // 检查登录名是否已存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getLoginName, user.getLoginName());
        if (userMapper.selectCount(queryWrapper) > 0) {
            throw new BusinessException("登录名已存在");
        }

        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean updateUser(User user) {
        // 如果修改了密码，需要加密
        User dbUser = userMapper.selectById(user.getId());
        if (dbUser != null && !dbUser.getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userMapper.updateById(user) > 0;
    }

    @Override
    public boolean deleteUser(Long id) {
        return userMapper.deleteById(id) > 0;
    }
}