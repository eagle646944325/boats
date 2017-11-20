package cn.gelk.service.sys.impl;

import cn.gelk.dao.TSysOrgMapper;
import cn.gelk.dao.TSysRoleMapper;
import cn.gelk.domain.TSysRole;
import cn.gelk.service.sys.RoleService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private TSysRoleMapper tSysRoleMapper;

    @Override
    public TSysRole selectById(Integer id) {
        return tSysRoleMapper.selectByPrimaryKey(id);
    }
}
