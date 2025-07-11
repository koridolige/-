package com.example.oceanbioserver.service;

import com.example.oceanbioserver.common.PageRequest;
import com.example.oceanbioserver.common.PageResponse;
import com.example.oceanbioserver.entity.Ebook;

import java.util.List;

/**
 * 电子书服务接口
 */
public interface EbookService {
    /**
     * 分页查询电子书
     * @param pageRequest 分页请求
     * @param categoryId 分类ID
     * @param name 名称
     * @return 分页结果
     */
    PageResponse<Ebook> listEbooksByPage(PageRequest pageRequest, Long categoryId, String name);

    /**
     * 根据ID查询电子书
     * @param id 电子书ID
     * @return 电子书
     */
    Ebook getEbook(Integer id);

    /**
     * 创建电子书
     * @param ebook 电子书
     * @return 是否成功
     */
    boolean createEbook(Ebook ebook);

    /**
     * 更新电子书
     * @param ebook 电子书
     * @return 是否成功
     */
    boolean updateEbook(Ebook ebook);

    /**
     * 删除电子书
     * @param id 电子书ID
     * @return 是否成功
     */
    boolean deleteEbook(Integer id);

    /**
     * 查询所有电子书
     * @return 电子书列表
     */
    List<Ebook> listAllEbooks();

}