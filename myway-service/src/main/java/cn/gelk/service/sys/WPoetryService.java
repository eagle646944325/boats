package cn.gelk.service.sys;

import cn.gelk.domain.*;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 系统管理
 */
public interface WPoetryService {


    WPoetry getPoetryInfoById(WPoetry wPoetry);

    List<WPoetry> getPoetryAll();

    List<WPoetry> selectPoetryByThemeId(String themeId);

    List<WPoetry> selectPoetryrecord(WPoetryRecord wPoetryRecord);


}
