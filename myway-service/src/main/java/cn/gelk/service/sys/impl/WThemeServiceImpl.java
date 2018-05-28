package cn.gelk.service.sys.impl;

import cn.gelk.dao.WPoetryCollectionMapper;
import cn.gelk.dao.WThemeMapper;
import cn.gelk.domain.WPoetryCollection;
import cn.gelk.domain.WTheme;
import cn.gelk.service.sys.WPoetryCollectionService;
import cn.gelk.service.sys.WThemeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统管理
 */
@Service
public class WThemeServiceImpl implements WThemeService {
    @Resource
    private WThemeMapper wThemeMapper;


    @Override
    public List<WTheme> selectThemeByParam(WTheme wTheme) {
        return wThemeMapper.select(wTheme);
    }
}
