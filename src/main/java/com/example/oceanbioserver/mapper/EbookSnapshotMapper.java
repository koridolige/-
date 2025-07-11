package com.example.oceanbioserver.mapper;

import com.example.oceanbioserver.entity.EbookSnapshot;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 电子书快照Mapper接口
 */
@Mapper
public interface EbookSnapshotMapper extends BaseMapper<EbookSnapshot> {

    /**
     * 获取统计数据
     * @return 统计数据
     */
    @Select("SELECT\n" +
            "    t1.`date` as `date`,\n" +
            "    SUM(t1.view_count) as viewCount,\n" +
            "    SUM(t1.vote_count) as voteCount,\n" +
            "    SUM(t1.view_increase) as viewIncrease,\n" +
            "    SUM(t1.vote_increase) as voteIncrease\n" +
            "FROM\n" +
            "    ebook_snapshot t1\n" +
            "WHERE\n" +
            "    t1.`date` >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)\n" +
            "GROUP BY\n" +
            "    t1.`date`\n" +
            "ORDER BY\n" +
            "    t1.`date` ASC")
    List<Map<String, Object>> getStatistic();

    /**
     * 获取30天快照统计
     * @return 统计数据
     */
    @Select("SELECT\n" +
            "    t1.`date` as `date`,\n" +
            "    t1.view_count as viewCount,\n" +
            "    t1.vote_count as voteCount,\n" +
            "    t1.view_increase as viewIncrease,\n" +
            "    t1.vote_increase as voteIncrease,\n" +
            "    t2.name as name\n" +
            "FROM\n" +
            "    ebook_snapshot t1, ebook t2\n" +
            "WHERE\n" +
            "    t1.ebook_id = t2.id AND\n" +
            "    t1.`date` = (SELECT MAX(`date`) FROM ebook_snapshot)\n" +
            "ORDER BY\n" +
            "    t1.view_count DESC, t1.vote_count DESC"
    )
    List<Map<String, Object>> getStatistic30();

    /**
     * 生成今日快照
     */
    @Select("INSERT INTO ebook_snapshot(ebook_id, `date`, view_count, vote_count, view_increase, vote_increase)\n" +
            "SELECT\n" +
            "    t1.id,\n" +
            "    CURDATE(),\n" +
            "    t1.view_count,\n" +
            "    t1.vote_count,\n" +
            "    (t1.view_count - IFNULL(t2.view_count, 0)),\n" +
            "    (t1.vote_count - IFNULL(t2.vote_count, 0))\n" +
            "FROM\n" +
            "    ebook t1\n" +
            "LEFT JOIN\n" +
            "    (SELECT ebook_id, view_count, vote_count FROM ebook_snapshot\n" +
            "    WHERE `date` = DATE_SUB(CURDATE(), INTERVAL 1 DAY)) t2\n" +
            "ON\n" +
            "    t1.id = t2.ebook_id\n" +
            "WHERE NOT EXISTS (\n" +
            "    SELECT 1 FROM ebook_snapshot es \n" +
            "    WHERE es.ebook_id = t1.id AND es.`date` = CURDATE()\n" +
            ")")
    void genSnapshot();
}


