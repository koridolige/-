package com.example.oceanbioserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.oceanbioserver.entity.EbookSnapshot;
import com.example.oceanbioserver.service.EbookSnapshotService;
import com.example.oceanbioserver.mapper.EbookSnapshotMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * 电子书快照服务实现类
 */
@Service
@Slf4j
public class EbookSnapshotServiceImpl implements EbookSnapshotService {

    @Autowired
    private EbookSnapshotMapper ebookSnapshotMapper;

    @Override
    public void genSnapshot() {
        log.info("生成今日电子书快照开始");
        ebookSnapshotMapper.genSnapshot();
        log.info("生成今日电子书快照结束");
    }

    @Override
    public List<Map<String, Object>> getStatistic() {
        log.info("获取电子书统计数据开始");
        List<Map<String, Object>> statistic = ebookSnapshotMapper.getStatistic();
        log.info("获取电子书统计数据结束，数据条数：{}", statistic != null ? statistic.size() : 0);
        return statistic;
    }

    @Override
    public List<Map<String, Object>> getStatistic30() {
        log.info("获取30天电子书统计数据开始");
        List<Map<String, Object>> statistic = ebookSnapshotMapper.getStatistic30();
        log.info("获取30天电子书统计数据结束，数据条数：{}", statistic != null ? statistic.size() : 0);
        return statistic;
    }

    @Override
    public EbookSnapshot getEbookSnapshot(Long id) {
        log.info("根据ID查询电子书快照开始，ID：{}", id);
        EbookSnapshot ebookSnapshot = ebookSnapshotMapper.selectById(id);
        if (ebookSnapshot == null) {
            log.warn("未找到ID为{}的电子书快照", id);
            throw new RuntimeException("未找到电子书快照");
        }
        log.info("根据ID查询电子书快照成功，ID：{}", id);
        return ebookSnapshot;
    }

    @Override
    public boolean createEbookSnapshot(EbookSnapshot ebookSnapshot) {
        log.info("创建电子书快照开始：{}", ebookSnapshot);
        if (ebookSnapshot.getEbookId() == null || ebookSnapshot.getDate() == null) {
            throw new RuntimeException("电子书ID和快照日期不能为空");
        }

        // 检查是否已存在相同ebookId和date的记录
        LambdaQueryWrapper<EbookSnapshot> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EbookSnapshot::getEbookId, ebookSnapshot.getEbookId())
                .eq(EbookSnapshot::getDate, ebookSnapshot.getDate());
        Long count = ebookSnapshotMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new RuntimeException("该电子书在指定日期已存在快照");
        }

        int result = ebookSnapshotMapper.insert(ebookSnapshot);
        log.info("创建电子书快照结束，结果：{}", result > 0);
        return result > 0;
    }

    @Override
    public boolean updateEbookSnapshot(EbookSnapshot ebookSnapshot) {
        log.info("更新电子书快照开始：{}", ebookSnapshot);
        if (ebookSnapshot.getId() == null) {
            throw new RuntimeException("快照ID不能为空");
        }

        // 检查快照是否存在
        EbookSnapshot existing = ebookSnapshotMapper.selectById(ebookSnapshot.getId());
        if (existing == null) {
            throw new RuntimeException("未找到要更新的电子书快照");
        }

        int result = ebookSnapshotMapper.updateById(ebookSnapshot);
        log.info("更新电子书快照结束，结果：{}", result > 0);
        return result > 0;
    }

    @Override
    public boolean deleteEbookSnapshot(Long id) {
        log.info("删除电子书快照开始，ID：{}", id);
        if (id == null) {
            throw new RuntimeException("快照ID不能为空");
        }

        int result = ebookSnapshotMapper.deleteById(id);
        log.info("删除电子书快照结束，结果：{}", result > 0);
        return result > 0;
    }
}