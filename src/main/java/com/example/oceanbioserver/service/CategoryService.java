package com.example.oceanbioserver.service;

import com.example.oceanbioserver.common.PageRequest;
import com.example.oceanbioserver.common.PageResponse;
import com.example.oceanbioserver.entity.Category;

import java.util.List;

/**
 * 分类服务接口
 */
public interface CategoryService {

    /**
     * 获取分类列表
     *
     * @return 分类列表
     */
    List<Category> listCategories();

    /**
     * 获取分类树
     *
     * @return 分类树
     */
    List<Category> getCategoryTree();

    /**
     * 分页查询分类列表
     *
     * @param pageRequest 分页请求
     * @param name        分类名称
     * @return 分类列表
     */
    PageResponse<Category> listCategoriesByPage(PageRequest pageRequest, String name);

    /**
     * 获取分类详情
     *
     * @param id 分类ID
     * @return 分类详情
     */
    Category getCategory(Long id);

    /**
     * 创建分类
     *
     * @param category 分类信息
     * @return 是否成功
     */
    Category createCategory(Category category);

    /**
     * 更新分类
     *
     * @param category 分类信息
     * @return 是否成功
     */
    boolean updateCategory(Category category);

    /**
     * 删除分类
     *
     * @param id 分类ID
     * @return 是否成功
     */
    boolean deleteCategory(Long id);
}