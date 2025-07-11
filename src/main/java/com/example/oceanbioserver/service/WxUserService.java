package com.example.oceanbioserver.service;

import com.example.oceanbioserver.common.Result;
import com.example.oceanbioserver.entity.WxUser;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;

/**
* @author lenovoYwJ
* @description 针对表【wx_user】的数据库操作Service
* @createDate 2025-07-07 18:50:48
*/
public interface WxUserService extends IService<WxUser> {
    Result register(WxUser user);
    Result login(WxUser wxUser, HttpServletResponse httpServletResponse);
    Result<WxUser> getUserInfo(String token);

}
