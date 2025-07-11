package com.example.oceanbioserver.controller;

import com.example.oceanbioserver.common.Result;
import com.example.oceanbioserver.entity.WxBanner;
import com.example.oceanbioserver.service.WxBannerService;
import com.example.oceanbioserver.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080") // 允许前端跨域访问
@RequestMapping("/api/banner") // 添加统一前缀
public class BannerController {

    @Autowired
    private WxBannerService bannerService;

    /**
     * 获取轮播图列表（需Token验证）
     */
    @GetMapping("/findBanner")
    public Result<List<WxBanner>> findBanner(@RequestHeader("Authorization") String authHeader) {
        // 1. 检查Token格式
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.failure(401, "无效的Token格式");
        }

        // 2. 提取Token并验证
        String token = authHeader.substring(7);
        if (!JWTUtils.Verify(token)) {
            return Result.failure(403, "Token已过期或无效");
        }

        // 3. 查询数据
        List<WxBanner> banners = bannerService.list();
        return Result.success(200, "成功", banners);
    }
}