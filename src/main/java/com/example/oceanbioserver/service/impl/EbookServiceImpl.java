package com.example.oceanbioserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.oceanbioserver.common.PageRequest;
import com.example.oceanbioserver.common.PageResponse;
import com.example.oceanbioserver.entity.Ebook;
import com.example.oceanbioserver.mapper.EbookMapper;
import com.example.oceanbioserver.service.EbookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 电子书服务实现类
 */
@Service
@Slf4j
public class EbookServiceImpl implements EbookService {

    @Autowired
    private EbookMapper ebookMapper;

    /**
     * 分页查询电子书
     *
     * @param pageRequest 分页请求
     * @param categoryId  分类ID
     * @param name        名称
     * @return 分页结果
     */
    @Override
    public PageResponse<Ebook> listEbooksByPage(PageRequest pageRequest, Long categoryId, String name) {
        Page<Ebook> page = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        LambdaQueryWrapper<Ebook> queryWrapper = new LambdaQueryWrapper<>();

        // 分类ID条件
        if (categoryId != null && categoryId > 0) {
            queryWrapper.eq(Ebook::getCategoryId, categoryId);
        }

        // 名称条件 - 改为模糊查询
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like(Ebook::getName, name);
        }

        queryWrapper.orderByDesc(Ebook::getId);
        Page<Ebook> ebookPage = ebookMapper.selectPage(page, queryWrapper);

        log.info("查询到 {} 条电子书数据，总数 {}", ebookPage.getRecords().size(), ebookPage.getTotal());
        if (ebookPage.getRecords() != null && !ebookPage.getRecords().isEmpty()) {
            for (Ebook ebook : ebookPage.getRecords()) {
                log.info("查询到电子书：{}", ebook);
            }
        }

        return new PageResponse<>(ebookPage.getRecords(), ebookPage.getTotal());
    }

    /**
     * 根据ID查询电子书
     *
     * @param id 电子书ID
     * @return 电子书信息
     */
    @Override
    public Ebook getEbook(Integer id) {
        //创建查询
        LambdaQueryWrapper<Ebook> queryWrapper = new LambdaQueryWrapper<>();
        //根据ID查询
        queryWrapper.eq(Ebook::getId, id);
        //查询
        Ebook ebook = ebookMapper.selectOne(queryWrapper);
        //返回结果
        return ebook;
    }

    /**
     * 创建电子书
     *
     * @param ebook 电子书信息
     * @return 创建结果
     */
    @Override
    public boolean createEbook(Ebook ebook) {
        //插入
        log.info("创建电子书：{}", ebook);
        int row = ebookMapper.insert(ebook);
        log.info("创建电子书结果：{},ID:{", row > 0, ebook.getId());
        return row > 0;
    }

    /**
     * 更新电子书
     *
     * @param ebook
     * @return
     */
    @Override
    public boolean updateEbook(Ebook ebook) {
        // 更新
        log.info("更新电子书：{}", ebook);
        int row = ebookMapper.updateById(ebook);
        log.info("更新电子书结果：{}", row > 0);
        return row > 0;
    }

    /**
     * 删除电子书
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteEbook(Integer id) {
        // 删除
        return ebookMapper.deleteById(id) > 0;
    }

    /**
     * 查询所有电子书
     *
     * @return 电子书列表
     */
    @Override
    public List<Ebook> listAllEbooks() {
        // 创建查询条件
        LambdaQueryWrapper<Ebook> queryWrapper = new LambdaQueryWrapper<>();
        // 构造查询条件
//        queryWrapper.orderByAsc(Ebook::getId);
        queryWrapper.orderByDesc(Ebook::getId);
        // 查询数据库
        List<Ebook> ebooks = ebookMapper.selectList(queryWrapper);
        log.info("查询到 {} 条电子书数据", ebooks.size());
        // 返回结果
        return ebooks;
    }
}