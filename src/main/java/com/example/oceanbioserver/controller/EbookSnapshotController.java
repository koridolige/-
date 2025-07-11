package com.example.oceanbioserver.controller;

import com.example.oceanbioserver.common.Result;
import com.example.oceanbioserver.entity.EbookSnapshot;
import com.example.oceanbioserver.service.EbookSnapshotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * 电子书快照控制器
 */
@RestController
@RequestMapping("/ebook-snapshot")
@Slf4j
public class EbookSnapshotController {

    @Autowired
    private EbookSnapshotService ebookSnapshotService;

    /**
     * 手动生成快照
     * @return 生成结果
     */
    @GetMapping("/gen")
    public Result<Boolean> genSnapshot() {
        try {
            log.info("开始手动生成电子书快照");
            ebookSnapshotService.genSnapshot();
            log.info("电子书快照生成成功");
            return Result.success(true);
        } catch (Exception e) {
            log.error("生成电子书快照失败", e);
            return Result.failure("生成电子书快照失败：" + e.getMessage());
        }
    }

    /**
     * 获取统计数据
     * @return 统计数据
     */
    @GetMapping("/get-statistic")
    public Result<List<Map<String, Object>>> getStatistic() {
        try {
            log.info("获取电子书统计数据");
            List<Map<String, Object>> statistic = ebookSnapshotService.getStatistic();
            log.info("获取电子书统计数据成功，数据条数：{}", statistic != null ? statistic.size() : 0);
            return Result.success(statistic);
        } catch (Exception e) {
            log.error("获取电子书统计数据失败", e);
            return Result.failure("获取电子书统计数据失败：" + e.getMessage());
        }
    }

    /**
     * 获取30天快照统计
     * @return 统计数据
     */
    @GetMapping("/get-30-statistic")
    public Result<List<Map<String, Object>>> getStatistic30() {
        try {
            log.info("获取电子书30天统计数据");
            List<Map<String, Object>> statistic = ebookSnapshotService.getStatistic30();
            log.info("获取电子书30天统计数据成功，数据条数：{}", statistic != null ? statistic.size() : 0);
            return Result.success(statistic);
        } catch (Exception e) {
            log.error("获取电子书30天统计数据失败", e);
            return Result.failure("获取电子书30天统计数据失败：" + e.getMessage());
        }
    }

    /**
     * 根据ID查询快照
     * @param id 快照ID
     * @return 快照信息
     */
    @GetMapping("/{id}")
    public Result<EbookSnapshot> getEbookSnapshot(@PathVariable Long id) {
        try {
            log.info("根据ID查询电子书快照，ID：{}", id);
            EbookSnapshot ebookSnapshot = ebookSnapshotService.getEbookSnapshot(id);
            log.info("查询电子书快照成功，ID：{}", id);
            return Result.success(ebookSnapshot);
        } catch (Exception e) {
            log.error("查询电子书快照失败，ID：{}", id, e);
            return Result.failure("查询电子书快照失败：" + e.getMessage());
        }
    }

    /**
     * 创建快照
     * @param ebookSnapshot 快照信息
     * @return 创建结果
     */
    @PostMapping
    public Result<Boolean> createEbookSnapshot(@RequestBody EbookSnapshot ebookSnapshot) {
        try {
            log.info("创建电子书快照：{}", ebookSnapshot);
            boolean result = ebookSnapshotService.createEbookSnapshot(ebookSnapshot);
            log.info("创建电子书快照结果：{}", result);
            return Result.success(result);
        } catch (Exception e) {
            log.error("创建电子书快照失败", e);
            return Result.failure("创建电子书快照失败：" + e.getMessage());
        }
    }

    /**
     * 更新快照
     * @param ebookSnapshot 快照信息
     * @return 更新结果
     */
    @PutMapping
    public Result<Boolean> updateEbookSnapshot(@RequestBody EbookSnapshot ebookSnapshot) {
        try {
            log.info("更新电子书快照：{}", ebookSnapshot);
            boolean result = ebookSnapshotService.updateEbookSnapshot(ebookSnapshot);
            log.info("更新电子书快照结果：{}", result);
            return Result.success(result);
        } catch (Exception e) {
            log.error("更新电子书快照失败", e);
            return Result.failure("更新电子书快照失败：" + e.getMessage());
        }
    }

    /**
     * 删除快照
     * @param id 快照ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteEbookSnapshot(@PathVariable Long id) {
        try {
            log.info("删除电子书快照，ID：{}", id);
            boolean result = ebookSnapshotService.deleteEbookSnapshot(id);
            log.info("删除电子书快照结果：{}", result);
            return Result.success(result);
        } catch (Exception e) {
            log.error("删除电子书快照失败，ID：{}", id, e);
            return Result.failure("删除电子书快照失败：" + e.getMessage());
        }
    }
} 