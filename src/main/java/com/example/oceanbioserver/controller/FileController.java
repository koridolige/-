package com.example.oceanbioserver.controller;

import com.example.oceanbioserver.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * 文件控制器
 */
@RestController
@RequestMapping("/file")
@CrossOrigin
@Slf4j
public class FileController {

    @Value("${file.upload-path}")
    private String uploadPath;

    /**
     * 上传文件
     * @param file 文件
     * @return 上传结果
     */
    @PostMapping("/upload")
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            log.error("上传文件为空");
            return Result.failure("上传文件不能为空");
        }
        
        try {
            // 检查上传目录是否存在，不存在则创建
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                log.info("创建上传目录: {}", uploadDir);
                Files.createDirectories(uploadDir);
            }
            
            // 生成文件名
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String filename = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
            
            // 保存文件
            Path filePath = uploadDir.resolve(filename);
            log.info("保存文件到: {}", filePath.toAbsolutePath());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            
            // 返回文件名（前端会通过/file/{filename}访问）
            log.info("文件上传成功，文件名: {}", filename);
            
            return Result.success(filename);
        } catch (IOException e) {
            log.error("文件上传失败", e);
            return Result.failure("文件上传失败: " + e.getMessage());
        }
    }
    
    /**
     * 获取文件
     * @param filename 文件名
     * @return 文件资源
     */
    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(uploadPath).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            
            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // 可以根据文件类型动态设置
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                log.error("文件不存在: {}", filename);
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            log.error("文件URL格式错误", e);
            return ResponseEntity.badRequest().build();
        }
    }
} 