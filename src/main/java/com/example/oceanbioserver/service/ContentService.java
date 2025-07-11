package com.example.oceanbioserver.service;


import com.example.oceanbioserver.entity.Content;

/**
 * 内容服务接口
 */
public interface ContentService {
    /**
     * 获取内容
     * @param docId 文档ID
     * @return 内容
     */
    Content getContent(Long docId);

    /**
     * 保存内容
     * @param content 内容
     * @return 是否成功
     */
    boolean saveContent(Content content);

    /**
     * 删除内容
     * @param docId 文档ID
     * @return 是否成功
     */
    boolean deleteContent(Long docId);
}