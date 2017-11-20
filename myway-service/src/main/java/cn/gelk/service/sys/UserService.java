package cn.gelk.service.sys;

import cn.gelk.domain.TSysUser;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface UserService {

    boolean saveOneTSysUser (TSysUser tSysUserSysUser);

    boolean deleteOneTSysUser (TSysUser tSysUserSysUser);

    boolean updateOneTSysUser (TSysUser tSysUserSysUser);

    TSysUser selectOneTSysUser (TSysUser tSysUserSysUser);

    PageInfo<TSysUser> selectListTSysUser(TSysUser tSysUserSysUser);
}
