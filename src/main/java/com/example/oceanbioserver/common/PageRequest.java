package com.example.oceanbioserver.common;

import lombok.Data;

/**
 * 分页请求类
 */
@Data
public class PageRequest {

    /**
     * 当前页码
     */
    private int page = 1;

    /**
     * 每页条数
     */
    private int size = 10;
} 