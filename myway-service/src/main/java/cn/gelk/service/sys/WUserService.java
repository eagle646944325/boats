package cn.gelk.service.sys;

import cn.gelk.domain.WUser; /**
 * 系统管理
 */
public interface WUserService {


    WUser selectWuser(WUser wUser);

    int updateWuser(WUser wUser1);

    int saveWuser(WUser wUser);
}
