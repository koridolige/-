package com.example.oceanbioserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.oceanbioserver.common.Result;
import com.example.oceanbioserver.common.ResultStatus;
import com.example.oceanbioserver.entity.WxUser;
import com.example.oceanbioserver.mapper.WxUserMapper;
import com.example.oceanbioserver.service.WxUserService;
import com.example.oceanbioserver.util.JWTUtils;
import com.example.oceanbioserver.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lenovoYwJ
 * @description 针对表【wx_user】的数据库操作Service实现
 * @createDate 2025-06-24 18:02:53
 */

/**
 * @author lenovoYwJ
 * @description 针对表【wx_user】的数据库操作Service实现
 * @createDate 2025-06-24 14:34:04
 */
@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser>
        implements WxUserService {

    @Autowired
    WxUserMapper wxUserMapper;
    @Autowired
    private HttpServletResponse httpServletResponse;

//    public Result register(WxUser wxUser) {
//        QueryWrapper<WxUser> queryWrapper = new QueryWrapper<WxUser>();
//        queryWrapper.eq("username", wxUser.getUsername());
//        WxUser selectOne = wxUserMapper.selectOne(queryWrapper);
//
//        if (selectOne != null) {
//            return Result.failure("用户已存在");
//        }
//        int row = wxUserMapper.insert(wxUser);
//        if (row > 0) {
//            return Result.success("注册成功");
//        }
//        //插入用户数据.
//        return Result.failure("注册失败");
//
//    }

    @Override
    public Result login(WxUser wxUser, HttpServletResponse httpServletResponse) {
        // 1. 构建查询条件 - 仅根据用户名查询
        QueryWrapper<WxUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", wxUser.getUsername());
        // 2. 执行查询
        WxUser selectOne = wxUserMapper.selectOne(queryWrapper);
        // 3. 用户不存在检查
        if (selectOne == null) {
            return Result.failure("登录失败，用户不存在");
        }
        // 4. 密码验证
        String rawPassword = wxUser.getPassword(); // 前端明文密码
        String encodedPassword = selectOne.getPassword(); // 数据库密文密码
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            return Result.failure("密码不正确");
        }
        // 5. 生成并处理JWT令牌
        String token = JWTUtils.createToken(wxUser.getUsername());
        // 6. 设置响应头
        httpServletResponse.setHeader(HttpHeaders.AUTHORIZATION, token);
        // 7. 存储到Redis
        RedisUtil.set(wxUser.getUsername(), token);
        // 8. 返回成功响应（不包含token）
        return Result.success("登录成功");
        //        return Result.success("登录成功", token);
    }


    @Override
    public Result<WxUser> getUserInfo(String authorization) {
        LambdaQueryWrapper<WxUser> queryWrapper = new LambdaQueryWrapper<>();
        String username = JWTUtils.getTokenUserInfo(authorization);
        queryWrapper.eq(WxUser::getUsername, username);
        WxUser user = wxUserMapper.selectOne(queryWrapper);
        if (user == null) {
            return Result.failure(ResultStatus.SERVER_ERROR, null);
        }
        return Result.success(user);
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Result register(WxUser wxUser) {
        // 密码校验
        if (wxUser.getPassword() == null) {
            return Result.failure("密码不能为空，请输入有效密码");
        } else if (wxUser.getPassword().trim().isEmpty()) {
            return Result.failure("密码不能为空，请输入有效密码");
        } else if (!isValidPassword(wxUser.getPassword())) {
            return Result.failure("密码强度不足，请包含字母、数字和特殊字符，且长度不少于8位");
        } else {
            // 密码加密处理
            String password = wxUser.getPassword();
            String encryptedPassword = passwordEncoder.encode(password);
            wxUser.setPassword(encryptedPassword);
        }

        // 检查用户名是否已存在
        QueryWrapper<WxUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", wxUser.getUsername());
        WxUser userOne = wxUserMapper.selectOne(queryWrapper);

        if (userOne != null) {
            return Result.failure("用户已存在");
        }

        // 插入新用户数据
        int row = wxUserMapper.insert(wxUser);
        if (row > 0) {
            return Result.success("注册成功");
        }

        return Result.failure("注册失败");
    }

    private boolean isValidPassword(String password) {
        // 密码长度至少为8位，且包含字母、数字和特殊字符
        return password.length() >= 8 &&
                password.matches(".*[a-zA-Z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[!@#$%^&*()].*");
    }

}





