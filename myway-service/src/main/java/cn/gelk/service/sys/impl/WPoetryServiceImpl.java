package cn.gelk.service.sys.impl;

import cn.gelk.dao.TSysRoleMapper;
import cn.gelk.dao.WPoetryMapper;
import cn.gelk.domain.*;
import cn.gelk.service.sys.*;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统管理
 */
@Service
public class WPoetryServiceImpl implements WPoetryService {
    @Resource
    private WPoetryMapper wPoetryMapper;

    @Override
    public WPoetry getPoetryInfoById(WPoetry wPoetry) {
        return wPoetryMapper.selectOne(wPoetry);

    }

    @Override
    public List<WPoetry> getPoetryAll() {
        return wPoetryMapper.selectAll();
    }

    @Override
    public List<WPoetry> selectPoetryByThemeId(String themeId) {
        WTheme theme=new WTheme();
        theme.setId(Integer.valueOf(themeId));
        return wPoetryMapper.selectPoetryByThemeId(theme);
    }

    @Override
    public List<WPoetry> selectPoetryrecord(WPoetryRecord wPoetryRecord) {
        List<WPoetry> list=wPoetryMapper.selectPoetryrecord(wPoetryRecord);
        return list;
    }
}
