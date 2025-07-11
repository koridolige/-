package com.example.oceanbioserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.oceanbioserver.entity.Content;
import com.example.oceanbioserver.mapper.ContentMapper;
import com.example.oceanbioserver.service.ContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 内容服务实现类
 */
@Service
@Slf4j
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentMapper contentMapper;

    @Override
    public Content getContent(Long docId) {
        // 根据文档ID查询内容
        LambdaQueryWrapper<Content> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Content::getDocId, docId);
        return contentMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean saveContent(Content content) {
        // 先查询是否存在
        Content existContent = getContent(content.getDocId());
        if (existContent == null) {
            // 不存在，插入
            return contentMapper.insert(content) > 0;
        } else {
            // 存在，更新
            content.setId(existContent.getId());
            return contentMapper.updateById(content) > 0;
        }
    }

    @Override
    public boolean deleteContent(Long docId) {
        // 根据文档ID删除内容
        LambdaQueryWrapper<Content> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Content::getDocId, docId);
        return contentMapper.delete(queryWrapper) > 0;
    }
}



