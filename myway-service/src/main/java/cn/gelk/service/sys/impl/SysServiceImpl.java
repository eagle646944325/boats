package cn.gelk.service.sys.impl;

import cn.gelk.dao.TSysUserMapper;
import cn.gelk.domain.TSysOrg;
import cn.gelk.domain.TSysRole;
import cn.gelk.domain.TSysUser;
import cn.gelk.service.sys.OrgService;
import cn.gelk.service.sys.RoleService;
import cn.gelk.service.sys.SysService;
import cn.gelk.service.sys.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 系统管理
 */
@Service
public class SysServiceImpl implements SysService{

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private OrgService orgService;

    @Override
    public boolean saveOneTSysUser(TSysUser tSysUserSysUser) {
        return userService.saveOneTSysUser(tSysUserSysUser);
    }

    @Override
    public boolean deleteOneTSysUser(TSysUser tSysUserSysUser) {
        return userService.deleteOneTSysUser(tSysUserSysUser);
    }

    @Override
    public boolean updateOneTSysUser(TSysUser tSysUserSysUser) {
        return userService.updateOneTSysUser(tSysUserSysUser);
    }

    @Override
    public TSysUser selectOneTSysUser(TSysUser tSysUserSysUser) {
        return userService.selectOneTSysUser(tSysUserSysUser);
    }

    @Override
    public TSysRole selectTSysRoleById(String id) {
        return roleService.selectById(Integer.parseInt(id));
    }

    @Override
    public TSysOrg selectTSysOrgById(String id) {
        return orgService.selectById(Integer.parseInt(id));
    }

    @Override
    public PageInfo<TSysUser> selectListTSysUser(TSysUser tSysUserSysUser) {
        PageInfo<TSysUser> list = userService.selectListTSysUser(tSysUserSysUser);
        list.getList().parallelStream().forEach(item -> {
            item.settSysOrg(orgService.selectById(Integer.valueOf(item.getOrgId())));
            item.settSysRole(roleService.selectById(Integer.valueOf(item.getRoleId())));
        });
        return list;
    }
}
