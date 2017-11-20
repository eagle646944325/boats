package cn.gelk.service.sys.impl;

import cn.gelk.dao.TSysOrgMapper;
import cn.gelk.dao.TSysUserMapper;
import cn.gelk.domain.TSysOrg;
import cn.gelk.service.sys.OrgService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class OrgServiceImpl implements OrgService {

    @Resource
    private TSysOrgMapper tSysOrgMapper;

    @Override
    public TSysOrg selectById(Integer id) {
        return tSysOrgMapper.selectByPrimaryKey(id);
    }
}
