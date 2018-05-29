package cn.gelk.dao;


import cn.gelk.domain.TSysUser;
import cn.gelk.domain.WPoetry;
import cn.gelk.domain.WPoetryCollection;
import cn.gelk.domain.WTheme;
import cn.gelk.markerInterface.MyBatisMapper;

import java.util.List;

public interface WPoetryMapper extends MyBatisMapper<WPoetry> {
    List<WPoetry> selectPoetryByThemeId(WTheme wTheme);

    List<WPoetry> selectCollection(WPoetryCollection wPoetryCollection);
}