package com.example.oceanbioserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.oceanbioserver.common.PageRequest;
import com.example.oceanbioserver.common.PageResponse;
import com.example.oceanbioserver.entity.Doc;
import com.example.oceanbioserver.mapper.DocMapper;
import com.example.oceanbioserver.service.DocService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 文档服务实现类
 */
@Service
@Slf4j
public class DocServiceImpl implements DocService {
    @Autowired
    private DocMapper docMapper;

    @Override
    public List<Doc> listDocsByEbookId(Long ebookId) {
        LambdaQueryWrapper<Doc> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Doc::getEbookId, ebookId);
        queryWrapper.orderByAsc(Doc::getSort);
        return docMapper.selectList(queryWrapper);
    }

    @Override
    public PageResponse<Doc> listDocsByPage(PageRequest pageRequest, Long ebookId) {
        Page<Doc> page = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        LambdaQueryWrapper<Doc> queryWrapper = new LambdaQueryWrapper<>();

        if (ebookId != null && ebookId > 0) {
            queryWrapper.eq(Doc::getEbookId, ebookId);
        }

        queryWrapper.orderByAsc(Doc::getSort);
        Page<Doc> docPage = docMapper.selectPage(page, queryWrapper);
        return new PageResponse<>(docPage.getRecords(), docPage.getTotal());
    }

    @Override
    public Doc getDoc(Long id) {
        return docMapper.selectById(id);
    }

    @Override
    public boolean createDoc(Doc doc) {
        return docMapper.insert(doc) > 0;
    }

    @Override
    public boolean updateDoc(Doc doc) {
        return docMapper.updateById(doc) > 0;
    }

    @Override
    public boolean deleteDoc(Long id) {
        LambdaQueryWrapper<Doc> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Doc::getParent, id);
        if (docMapper.selectCount(queryWrapper) > 0) {
            return false;
        }
        return docMapper.deleteById(id) > 0;
    }

    @Override
    public List<Doc> getDocTree(Long ebookId) {
        List<Doc> allDocs = listDocsByEbookId(ebookId);

        Map<Long, List<Doc>> parentMap = new HashMap<>();
        for (Doc doc : allDocs) {
            List<Doc> children = parentMap.computeIfAbsent(doc.getParent(), k -> new ArrayList<>());
            children.add(doc);
        }

        return buildTree(parentMap, 0L);
    }

    private List<Doc> buildTree(Map<Long, List<Doc>> parentMap, Long parentId) {
        List<Doc> children = parentMap.get(parentId);
        if (children == null) {
            return new ArrayList<>();
        }

        for (Doc doc : children) {
            List<Doc> docChildren = buildTree(parentMap, doc.getId());
            doc.setChildren(docChildren);
        }
        return children;
    }
}




