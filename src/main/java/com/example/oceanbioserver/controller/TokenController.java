package com.example.oceanbioserver.controller;

import com.example.oceanbioserver.common.Result;
import com.example.oceanbioserver.util.RedisUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/WxUser")
public class TokenController {
    @PostMapping("/getToken")
    public Result getToken(String username) {
        if (username==null){
            return Result.failure("用户信息不能为空");
        }
        return Result.success(RedisUtil.get(username));
    }
}
