package com.example.oceanbioserver.mapper;

import com.example.oceanbioserver.entity.Doc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 文档Mapper接口
 */
@Mapper
public interface DocMapper extends BaseMapper<Doc> {

    /**
     * 增加阅读数
     * @param id 文档ID
     */
    @Update("update doc set view_count = view_count + 1 where id = #{id}")
    void increaseViewCount(@Param("id") Long id);

    /**
     * 增加点赞数
     * @param id 文档ID
     */
    @Update("update doc set vote_count = vote_count + 1 where id = #{id}")
    void increaseVoteCount(@Param("id") Long id);
}



