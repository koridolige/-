package com.example.oceanbioserver.controller;

import com.example.oceanbioserver.common.PageRequest;
import com.example.oceanbioserver.common.PageResponse;
import com.example.oceanbioserver.common.Result;
import com.example.oceanbioserver.entity.Content;
import com.example.oceanbioserver.entity.Doc;
import com.example.oceanbioserver.service.ContentService;
import com.example.oceanbioserver.service.DocService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文档控制器
 */
@RestController
@RequestMapping("/doc")
@Slf4j
public class DocController {
    @Autowired
    private DocService docService;

    @Autowired
    private ContentService contentService;

    @GetMapping("/list/{ebookId}")
    public Result<List<Doc>> list(@PathVariable Long ebookId) {
        List<Doc> docs = docService.listDocsByEbookId(ebookId);
        return Result.success(docs);
    }

    @GetMapping("/tree/{ebookId}")
    public Result<List<Doc>> tree(@PathVariable Long ebookId) {
        List<Doc> docTree = docService.getDocTree(ebookId);
        return Result.success(docTree);
    }

    @GetMapping("/page")
    public Result<PageResponse<Doc>> page(PageRequest pageRequest,
                                          @RequestParam(required = false) Long ebookId) {
        PageResponse<Doc> pageResponse = docService.listDocsByPage(pageRequest, ebookId);
        return Result.success(pageResponse);
    }

    @GetMapping("/{id}")
    public Result<Doc> getDoc(@PathVariable Long id) {
        Doc doc = docService.getDoc(id);
        return Result.success(doc);
    }

    @PostMapping
    public Result<Boolean> createDoc(@RequestBody Doc doc) {
        boolean result = docService.createDoc(doc);
        return Result.success(result);
    }

    @PutMapping
    public Result<Boolean> updateDoc(@RequestBody Doc doc) {
        boolean result = docService.updateDoc(doc);
        return Result.success(result);
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> deleteDoc(@PathVariable Long id) {
        boolean result = docService.deleteDoc(id);
        if (result) {
            contentService.deleteContent(id);
        }
        return Result.success(result);
    }

    @GetMapping("/content/{id}")
    public Result<Content> getContent(@PathVariable Long id) {
        Content content = contentService.getContent(id);
        if (content == null) {
            content = new Content();
            content.setDocId(id);
            content.setContent("");
        }
        return Result.success(content);
    }

    @PostMapping("/content")
    public Result<Boolean> saveContent(@RequestBody Content content) {
        boolean result = contentService.saveContent(content);
        return Result.success(result);
    }
}