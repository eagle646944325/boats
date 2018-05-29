package cn.gelk.service.sys.impl;

import cn.gelk.dao.WPoetryCollectionMapper;
import cn.gelk.dao.WPoetryMapper;
import cn.gelk.domain.WPoetry;
import cn.gelk.domain.WPoetryCollection;
import cn.gelk.service.sys.WPoetryCollectionService;
import cn.gelk.service.sys.WPoetryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统管理
 */
@Service
public class WPoetryCollectionServiceImpl implements WPoetryCollectionService {
    @Resource
    private WPoetryCollectionMapper wPoetryCollectionMapper;

    @Resource
    private WPoetryMapper wPoetryMapper;

    @Override
    public int insertWPoetryCollection(WPoetryCollection wPoetryCollection) {
        return  wPoetryCollectionMapper.insert(wPoetryCollection);
    }

    @Override
    public int deleteCollection(WPoetryCollection wPoetryCollection) {
       return  wPoetryCollectionMapper.delete(wPoetryCollection);
    }

    @Override
    public List<WPoetry> selectCollection(WPoetryCollection wPoetryCollection) {
        return  wPoetryMapper.selectCollection(wPoetryCollection);
    }
}
