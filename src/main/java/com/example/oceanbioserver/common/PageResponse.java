package com.example.oceanbioserver.common;

import lombok.Data;

import java.util.List;

/**
 * 分页响应类
 * @param <T> 数据类型
 */
@Data
public class PageResponse<T> {

    /**
     * 总记录数
     */
    private long total;

    /**
     * 当前页数据列表
     */
    private List<T> list;

    /**
     * 构造方法
     * @param list 数据列表
     * @param total 总记录数
     */
    public PageResponse(List<T> list, long total) {
        this.list = list;
        this.total = total;
    }
} 