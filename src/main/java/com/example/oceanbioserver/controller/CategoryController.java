package com.example.oceanbioserver.controller;

import com.example.oceanbioserver.common.PageRequest;
import com.example.oceanbioserver.common.PageResponse;
import com.example.oceanbioserver.common.Result;
import com.example.oceanbioserver.entity.Category;
import com.example.oceanbioserver.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类控制器
 */
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询所有分类
     * @return 分类列表
     */
    @GetMapping("/all")
    public Result<List<Category>> all() {
        log.info("查询所有分类");
        List<Category> categories = categoryService.listCategories();
        log.info("查询到 {} 条分类数据", categories.size());
        return Result.success(categories);
    }

    /**
     * 查询分类树
     * @return 分类树
     */
    @GetMapping("/tree")
    public Result<List<Category>> tree() {
        log.info("查询分类树");
        List<Category> categoryTree = categoryService.getCategoryTree();
        log.info("查询到 {} 条分类树数据", categoryTree.size());
        return Result.success(categoryTree);
    }

    /**
     * 分页查询分类
     * @param pageRequest 分页请求
     * @param name 分类名称
     * @return 分页结果
     */
    @GetMapping("/list")
    public Result<PageResponse<Category>> list(PageRequest pageRequest, String name) {
        log.info("分页查询分类: page={}, size={}, name={}", pageRequest.getPage(), pageRequest.getSize(), name);
        PageResponse<Category> pageResponse = categoryService.listCategoriesByPage(pageRequest, name);
        log.info("查询到 {} 条分类数据，总数 {}", 
                pageResponse.getList().size(), 
                pageResponse.getTotal());
        return Result.success(pageResponse);
    }

    /**
     * 根据ID查询分类
     * @param id 分类ID
     * @return 分类信息
     */
    @GetMapping("/{id}")
    public Result<Category> getCategory(@PathVariable Long id) {
        log.info("根据ID查询分类: id={}", id);
        Category category = categoryService.getCategory(id);
        if (category != null) {
            log.info("查询到分类: {}", category);
        } else {
            log.warn("未查询到分类: id={}", id);
        }
        return Result.success(category);
    }

    /**
     * 创建分类
     * @param category 分类信息
     * @return 创建结果
     */
    @PostMapping
    public Result<Category> createCategory(@RequestBody Category category) {
        try {
            Category savedCategory = categoryService.createCategory(category);
            return Result.success(savedCategory); // 返回带id的完整数据
        } catch (Exception e) {
            log.error("添加分类异常", e);
            return Result.failure(e.getMessage());
        }
    }

    /**
     * 更新分类
     * @param category 分类信息
     * @return 更新结果
     */
    @PutMapping
    public Result<Boolean> updateCategory(@RequestBody Category category) {
        log.info("更新分类: {}", category);
        boolean result = categoryService.updateCategory(category);
        log.info("更新分类结果: {}", result);
        return Result.success(result);
    }

    /**
     * 删除分类
     * @param id 分类ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteCategory(@PathVariable Long id) {
        log.info("删除分类: id={}", id);
        boolean result = categoryService.deleteCategory(id);
        log.info("删除分类结果: {}", result);
        return Result.success(result);
    }
} 