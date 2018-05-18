package cn.gelk.service.sys.impl;

import cn.gelk.dao.TSysRoleMapper;
import cn.gelk.dao.WPoetryMapper;
import cn.gelk.domain.TSysOrg;
import cn.gelk.domain.TSysRole;
import cn.gelk.domain.TSysUser;
import cn.gelk.domain.WPoetry;
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
}
