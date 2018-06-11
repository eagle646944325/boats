package cn.gelk.dao;


import cn.gelk.domain.*;
import cn.gelk.markerInterface.MyBatisMapper;

import java.util.List;

public interface WPoetryMapper extends MyBatisMapper<WPoetry> {
    List<WPoetry> selectPoetryByThemeId(WTheme wTheme);

    List<WPoetry> selectCollection(WPoetryCollection wPoetryCollection);

    List<WPoetry> selectPoetryrecord(WPoetryRecord wPoetryRecord);
}