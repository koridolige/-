package com.example.oceanbioserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.oceanbioserver.common.Result;
import com.example.oceanbioserver.entity.WxGoods;
import com.example.oceanbioserver.service.WxGoodsService;
import com.example.oceanbioserver.mapper.WxGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author lenovoYwJ
* @description 针对表【wx_goods】的数据库操作Service实现
* @createDate 2025-07-07 19:40:53
*/
@Service
public class WxGoodsServiceImpl extends ServiceImpl<WxGoodsMapper, WxGoods>
        implements WxGoodsService {

    @Autowired
    WxGoodsMapper wxGoodsMapper;

    /**
     * 关键字为空时，默认查询所有商品并分页
     */
    @Override
    public Result getPageByGoods(Integer currentPage, Integer pageSize) {
        //1.创建分页对象
        Page<WxGoods> page = new Page<>(currentPage, pageSize);
        //2.执行分页查询（无条件）
        Page<WxGoods> wxGoodsPage = wxGoodsMapper.selectPage(page, null);
        //3.返回分页结果
        return Result.success(wxGoodsPage);
    }

    /**
     * 方式二：根据关键词模糊查询商品并分页（使用QueryWrapper）
     * 根据商品名称、类别或描述字段进行模糊匹配
     */
    @Override
    public Result getPageByKey(String searchKey, Integer currentPage, Integer pageSize) {
        //1.创建分页对象
        Page<WxGoods> page = new Page<>(currentPage, pageSize);
        //2.构建查询条件
        QueryWrapper<WxGoods> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(searchKey)) {
            queryWrapper.and(wrapper -> wrapper.like("goods_name", searchKey)
                    .or().like("category", searchKey)
                    .or().like("description", searchKey));
        }
        //3.执行分页查询
        Page<WxGoods> resultPage = wxGoodsMapper.selectPage(page, queryWrapper);
        return Result.success(resultPage);
    }

    @Override
    public Result findByCategory(String category) {
        // 新增加的实现逻辑
        QueryWrapper<WxGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category", category);
        List<WxGoods> goodsList = baseMapper.selectList(queryWrapper);
        return Result.success(goodsList);
    }

    @Override
    public List<String> findCategoryList() {
        // 使用MyBatis-Plus的LambdaQueryWrapper去重查询
        return this.lambdaQuery()
                .select(WxGoods::getCategory)  // 只查询category字段
                .groupBy(WxGoods::getCategory) // 按分类分组去重
                .list()
                .stream()
                .map(WxGoods::getCategory)     // 提取分类名称
                .collect(Collectors.toList());
    }

}





