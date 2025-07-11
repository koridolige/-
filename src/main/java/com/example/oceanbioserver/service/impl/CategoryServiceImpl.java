package com.example.oceanbioserver.service.impl;

import com.example.oceanbioserver.common.PageRequest;
import com.example.oceanbioserver.common.PageResponse;
import com.example.oceanbioserver.entity.Category;
import com.example.oceanbioserver.service.CategoryService;
import com.example.oceanbioserver.mapper.CategoryMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 分类服务实现类
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> listCategories() {
        log.info("查询所有分类数据");
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        return categoryMapper.selectList(queryWrapper);
    }

    @Override
    public List<Category> getCategoryTree() {
        log.info("构建分类树结构");
        List<Category> allCategories = listCategories();

        // 按父ID分组
        Map<Long, List<Category>> parentMap = allCategories.stream()
                .collect(Collectors.groupingBy(Category::getParent));

        // 构建树形结构
        return buildTree(parentMap, 0L); // 假设顶级分类的parent为0
    }

    /**
     * 构建树形结构
     *
     * @param parentMap 按父ID分组的分类
     * @param parentId  父ID
     * @return 树形结构
     */
    private List<Category> buildTree(Map<Long, List<Category>> parentMap, Long parentId) {
        List<Category> children = parentMap.get(parentId);
        if (children == null) {
            return new ArrayList<>();
        }

        for (Category category : children) {
            List<Category> categoryChildren = buildTree(parentMap, category.getId());
            // 需要为Category添加children字段和对应的getter/setter
            category.setChildren(categoryChildren);
        }

        return children;
    }

    @Override
    public PageResponse<Category> listCategoriesByPage(PageRequest pageRequest, String name) {
        log.info("分页查询分类列表: page={}, size={}, name={}",
                pageRequest.getPage(), pageRequest.getSize(), name);

        // 1. 构建查询条件
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);  // 按名称模糊查询
        }
        queryWrapper.orderByAsc("sort");      // 按sort字段升序排序

        // 2. 执行分页查询
        IPage<Category> page = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        IPage<Category> resultPage = categoryMapper.selectPage(page, queryWrapper);

        // 3. 返回仅包含list和total的PageResponse
        return new PageResponse<>(
                resultPage.getRecords(),   // 当前页数据列表
                resultPage.getTotal()     // 总记录数
        );
    }

    @Override
    public Category getCategory(Long id) {
        log.info("根据ID查询分类：{}", id);
        return categoryMapper.selectById(id);
    }

    @Override
    public Category createCategory(Category category) {
        int result = categoryMapper.insert(category);
        if (result > 0) {
            return category; // 返回带id的完整对象
        }
        throw new RuntimeException("添加分类失败");
    }

    @Override
    public boolean updateCategory(Category category) {
        log.info("更新分类：{}", category);
        int result = categoryMapper.updateById(category);
        return result > 0;
    }

    @Override
    public boolean deleteCategory(Long id) {
        log.info("删除分类：{}", id);
        // 检查是否有子分类
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent", id);
        Long count = categoryMapper.selectCount(queryWrapper);
        if (count > 0) {
            log.warn("无法删除分类，存在子分类：{}", id);
            return false;
        }

        int result = categoryMapper.deleteById(id);
        return result > 0;
    }
}