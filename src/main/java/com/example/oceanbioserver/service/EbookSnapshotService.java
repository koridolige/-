package com.example.oceanbioserver.service;

import com.example.oceanbioserver.entity.EbookSnapshot;
import java.util.List;
import java.util.Map;

/**
 * 电子书快照服务接口
 */
public interface EbookSnapshotService {

    /**
     * 生成快照
     */
    void genSnapshot();

    /**
     * 获取统计数据
     * @return 统计数据
     */
    List<Map<String, Object>> getStatistic();

    /**
     * 获取30天快照统计
     * @return 统计数据
     */
    List<Map<String, Object>> getStatistic30();

    /**
     * 根据ID查询快照
     * @param id 快照ID
     * @return 快照
     */
    EbookSnapshot getEbookSnapshot(Long id);

    /**
     * 创建快照
     * @param ebookSnapshot 快照
     * @return 是否成功
     */
    boolean createEbookSnapshot(EbookSnapshot ebookSnapshot);

    /**
     * 更新快照
     * @param ebookSnapshot 快照
     * @return 是否成功
     */
    boolean updateEbookSnapshot(EbookSnapshot ebookSnapshot);

    /**
     * 删除快照
     * @param id 快照ID
     * @return 是否成功
     */
    boolean deleteEbookSnapshot(Long id);
}