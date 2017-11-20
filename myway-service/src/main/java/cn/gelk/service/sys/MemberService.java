package cn.gelk.service.sys;

import cn.gelk.domain.TMember;
import cn.gelk.domain.TSysOrg;
import cn.gelk.domain.TSysRole;
import cn.gelk.domain.TMember;
import com.github.pagehelper.PageInfo;

/**
 * 系统管理
 */
public interface MemberService {


    boolean deleteOneTMember(TMember tMember);

    public PageInfo<TMember> selectListTMember(TMember tMember);

    public TMember selectTMemberById(String  id);

    boolean updateTMember(TMember tMember);
}
