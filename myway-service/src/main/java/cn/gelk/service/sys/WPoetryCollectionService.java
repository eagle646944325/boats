package cn.gelk.service.sys;

import cn.gelk.domain.WPoetry;
import cn.gelk.domain.WPoetryCollection;

import java.util.List;

/**
 * 系统管理
 */
public interface WPoetryCollectionService {


    int insertWPoetryCollection(WPoetryCollection wPoetryCollection);

    int deleteCollection(WPoetryCollection wPoetryCollection);

    List<WPoetry> selectCollection(WPoetryCollection wPoetryCollection);
}
