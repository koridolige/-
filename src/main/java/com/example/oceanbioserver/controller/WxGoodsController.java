package com.example.oceanbioserver.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.oceanbioserver.common.Result;
import com.example.oceanbioserver.common.ResultStatus;
import com.example.oceanbioserver.entity.WxGoods;
import com.example.oceanbioserver.service.WxGoodsService;
import com.example.oceanbioserver.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/goods")
public class WxGoodsController {
    @Autowired
    private WxGoodsService wxGoodsService;

    /**
     * 分页查询商品列表
     *
     * @param token       用户身份验证令牌（从请求头获取）
     * @param currentPage 当前页码（默认为1）
     * @param pageSize    每页数据量（默认为6）
     * @return 分页结果
     */
    @RequestMapping("/getPageMyGoods")
    public Result getPageMyGoods(
            @RequestHeader("Authorization") String token,
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "6") Integer pageSize) throws InterruptedException {

        // 验证Token合法性
        if (!JWTUtils.Verify(token)) {
            return Result.failure("Token不合法");
        }

        // 模拟下拉刷新延迟（实际项目可移除）
        Thread.sleep(1000);

        // 1. 创建分页对象
        Page<WxGoods> page = new Page<>(currentPage, pageSize);

        // 2. 执行分页查询（MyBatis-Plus物理分页）
        IPage<WxGoods> goodsPage = wxGoodsService.page(page);

        // 3. 返回分页数据
        return Result.success(goodsPage);
    }

    /**
     * 模糊查询+分页查询
     *
     * @param token       用户身份验证令牌（从请求头获取）
     * @param currentPage 当前页码（默认为1）
     * @param pageSize    每页数据量（默认为6）
     */
    @RequestMapping("/getPageQueryByGoods")
    public Result getPageQueryByGoods(
            @RequestHeader("Authorization") String token,
            String searchKey,
            int currentPage,
            int pageSize) throws InterruptedException {

        if (JWTUtils.Verify(token)) { // 调用 `JWTUtils.verify` 方法验证 Token 是否合法
            Result result;
            if (StringUtils.isBlank(searchKey)) { // 关键字为空 StringUtils.isBlank(searchKey)返回true 计算结果并执行
                // 关键字为空 默认返回所有
                result = wxGoodsService.getPageByGoods(currentPage, pageSize);
            } else {
                // 关键字不为空 按照输入的条件进行查询
                result = wxGoodsService. getPageByKey(searchKey, currentPage, pageSize);
            }
            return result;
        } else {
            return Result.failure("token不合法");
        }
    }

    /**
     * 根据商品类别获取商品列表
     *
     * @param authorization 授权令牌，从请求头获取
     * @param category      商品类别，从请求参数获取
     * @return Result对象，包含商品列表
     */
    @GetMapping("/findByCategory")
    public Result findByCategory(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("category") String category) {

        if (!JWTUtils.Verify(authorization)) {
            return Result.failure(ResultStatus.UNAUTHORIZED);
        }

        // 通过实例调用方法
        return wxGoodsService.findByCategory(category);
    }

    /**
     * 获取所有商品类别列表
     * @param authorization 授权令牌
     * @return Result对象，包含分类列表
     */
    @GetMapping("/findCategoryList")
    public Result<List<String>> findCategoryList(
            @RequestHeader("Authorization") String authorization) {

        // 1. Token验证（与findByCategory风格一致）
        if (!JWTUtils.Verify(authorization)) {
            return Result.failure(ResultStatus.UNAUTHORIZED);
        }

        // 2. 调用Service获取分类列表
        List<String> categories = wxGoodsService.findCategoryList();

        // 3. 返回统一响应结构
        return Result.success(categories);
    }

}
