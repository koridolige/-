package com.example.oceanbioserver.controller;

import com.example.oceanbioserver.common.Result;
import com.example.oceanbioserver.common.ResultStatus;
import com.example.oceanbioserver.entity.WxUser;
import com.example.oceanbioserver.service.WxUserService;
import com.example.oceanbioserver.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("WxUser")
@CrossOrigin
public class WxUserController {
    @Autowired
    WxUserService wxUserService;

    @PostMapping("/register")
    public Result reqister(WxUser wxUser) {
        Result register = wxUserService.register(wxUser);
        System.out.println("用户注册返回的数据是" + register);
        return register;
    }

//    @PostMapping("/login")
//    public Result login(WxUser wxUser) {
//        Result login = wxUserService.login(wxUser);
//        System.out.println("用户注册返回的数据是" + login);
//        return login;
//    }

    /**
     * 用户登录
     * @param wxUser 用户信息（包含用户名和密码）
     * @param httpServletResponse HTTP响应对象
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result login(WxUser wxUser, HttpServletResponse httpServletResponse) {
        // 调用服务层进行登录验证
        Result login = wxUserService.login(wxUser, httpServletResponse);

        // 打印登录结果（生产环境应移除）
        System.out.println("登录返回数据: " + login);

        return login;
    }

    @GetMapping("/findUserInfo")
    public Result<WxUser> findUserInfo(@RequestHeader("Authorization") String authorization) {
        if (JWTUtils.Verify(authorization)) {
            return wxUserService.getUserInfo(authorization);
        }
        return Result.failure(ResultStatus.SERVER_ERROR, null);
    }

    @GetMapping("/checkTokenStatus")
    public Result checkTokenStatus(@RequestHeader("Authorization") String authorization) {
        if (JWTUtils.Verify(authorization)) {
            return Result.success("token是有效的");
        }
        return Result.failure("token是无效的");
    }

}

