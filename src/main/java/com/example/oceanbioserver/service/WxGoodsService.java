package com.example.oceanbioserver.service;

import com.example.oceanbioserver.common.Result;
import com.example.oceanbioserver.entity.WxGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author lenovoYwJ
* @description 针对表【wx_goods】的数据库操作Service
* @createDate 2025-07-07 19:40:53
*/
public interface WxGoodsService extends IService<WxGoods> {
    Result getPageByGoods(Integer currentPage, Integer pageSize);

    Result getPageByKey(String searchKey, Integer currentPage, Integer pageSize);

    Result findByCategory(String category);

    List<String> findCategoryList();
}