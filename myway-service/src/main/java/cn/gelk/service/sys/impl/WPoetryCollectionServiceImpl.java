package cn.gelk.service.sys.impl;

import cn.gelk.dao.WPoetryCollectionMapper;
import cn.gelk.dao.WPoetryMapper;
import cn.gelk.domain.WPoetryCollection;
import cn.gelk.service.sys.WPoetryCollectionService;
import cn.gelk.service.sys.WPoetryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 系统管理
 */
@Service
public class WPoetryCollectionServiceImpl implements WPoetryCollectionService {
    @Resource
    private WPoetryCollectionMapper wPoetryCollectionMapper;

    @Override
    public int insertWPoetryCollection(WPoetryCollection wPoetryCollection) {
        return  wPoetryCollectionMapper.insert(wPoetryCollection);
    }
}
