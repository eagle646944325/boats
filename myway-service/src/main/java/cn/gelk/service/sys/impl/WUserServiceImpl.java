package cn.gelk.service.sys.impl;

import cn.gelk.dao.WUserMapper;
import cn.gelk.domain.WUser;
import cn.gelk.service.sys.WPoetryService;
import cn.gelk.service.sys.WUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 系统管理
 */
@Service
public class WUserServiceImpl implements WUserService {

    @Resource
    private WUserMapper wUserMapper;
    @Override
    public WUser selectWuser(WUser wUser) {
        return wUserMapper.selectOne(wUser);
    }

    @Override
    public int updateWuser(WUser wUser1) {
       return  wUserMapper.updateByPrimaryKey(wUser1);
    }

    @Override
    public int  saveWuser(WUser wUser) {
        return wUserMapper.insert(wUser);
    }
}
