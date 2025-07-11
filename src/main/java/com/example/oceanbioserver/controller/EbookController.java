package com.example.oceanbioserver.controller;

import com.example.oceanbioserver.common.PageRequest;
import com.example.oceanbioserver.common.PageResponse;
import com.example.oceanbioserver.common.Result;
import com.example.oceanbioserver.entity.Ebook;
import com.example.oceanbioserver.service.EbookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 电子书控制器
 */
@RestController
@RequestMapping("/ebook")
@Slf4j
public class EbookController {

    @Autowired
    private EbookService ebookService;

    /**
     * 查询所有电子书
     * @return 电子书列表
     */
    @GetMapping("/all")
    public Result<List<Ebook>> all() {
        List<Ebook> ebooks = ebookService.listAllEbooks();
        return Result.success(ebooks);
    }

    /**
     * 分页查询电子书
     * @param pageRequest 分页请求
     * @param categoryId 分类ID
     * @param name 名称
     * @return 分页结果
     */
    @GetMapping("/list")
    public Result<PageResponse<Ebook>> list(PageRequest pageRequest,
                                            @RequestParam(required = false) Long categoryId,
                                            @RequestParam(required = false) String name) {
        PageResponse<Ebook> pageResponse = ebookService.listEbooksByPage(pageRequest, categoryId, name);
        return Result.success(pageResponse);
    }

    /**
     * 根据ID查询电子书
     * @param id 电子书ID
     * @return 电子书信息
     */
    @GetMapping("/{id}")
    public Result<Ebook> getEbook(@PathVariable Integer id) {
        Ebook ebook = ebookService.getEbook(id);
        return Result.success(ebook);
    }

    /**
     * 创建电子书
     * @param ebook 电子书信息
     * @return 创建结果
     */
    @PostMapping
    public Result<Boolean> createEbook(@RequestBody Ebook ebook) {
        log.info("创建电子书: {}", ebook);
        boolean result = ebookService.createEbook(ebook);
        return Result.success(result);
    }

    /**
     * 更新电子书
     * @param ebook 电子书信息
     * @return 更新结果
     */
    @PutMapping
    public Result<Boolean> updateEbook(@RequestBody Ebook ebook) {
        log.info("更新电子书: {}", ebook);
        boolean result = ebookService.updateEbook(ebook);
        return Result.success(result);
    }

    /**
     * 删除电子书
     * @param id 电子书ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteEbook(@PathVariable Integer id) {
        boolean result = ebookService.deleteEbook(id);
        return Result.success(result);
    }
} 