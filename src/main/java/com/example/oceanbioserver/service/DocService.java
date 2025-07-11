package com.example.oceanbioserver.service;

import com.example.oceanbioserver.common.PageRequest;
import com.example.oceanbioserver.common.PageResponse;
import com.example.oceanbioserver.entity.Doc;

import java.util.List;

/**
 * 文档服务接口
 */
public interface DocService {
    /**
     * 根据电子书ID查询文档列表
     * @param ebookId 电子书ID
     * @return 文档列表
     */
    List<Doc> listDocsByEbookId(Long ebookId);

    /**
     * 分页查询文档
     * @param pageRequest 分页请求
     * @param ebookId 电子书ID
     * @return 分页结果
     */
    PageResponse<Doc> listDocsByPage(PageRequest pageRequest, Long ebookId);

    /**
     * 获取文档树
     * @param ebookId 电子书ID
     * @return 文档树
     */
    List<Doc> getDocTree(Long ebookId);

    /**
     * 获取文档
     * @param id 文档ID
     * @return 文档
     */
    Doc getDoc(Long id);

    /**
     * 创建文档
     * @param doc 文档
     * @return 是否成功
     */
    boolean createDoc(Doc doc);

    /**
     * 更新文档
     * @param doc 文档
     * @return 是否成功
     */
    boolean updateDoc(Doc doc);

    /**
     * 删除文档
     * @param id 文档ID
     * @return 是否成功
     */
    boolean deleteDoc(Long id);
}