package cn.gelk.service.sys.impl;

import cn.gelk.dao.TMemberMapper;
import cn.gelk.dao.TSysOrgMapper;
import cn.gelk.domain.TMember;
import cn.gelk.domain.TSysOrg;
import cn.gelk.domain.TSysRole;
import cn.gelk.domain.TSysUser;
import cn.gelk.service.sys.MemberService;
import cn.gelk.service.sys.OrgService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private TMemberMapper tMemberMapper;



    @Override
    public boolean deleteOneTMember(TMember tMember) {
        return tMemberMapper.delete(tMember)>0;
    }

    @Override
    public PageInfo<TMember> selectListTMember(TMember tMember) {
        PageInfo<TMember> pageInfo = null;
        Example example = new Example(TMember.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(tMember.getMemberName())){
            criteria.andLike("memberName",tMember.getMemberName());
        }

        if (!StringUtils.isEmpty(tMember.getPhone())){
            criteria.andLike("phone",tMember.getPhone());
        }
        example.orderBy("id").desc();
        pageInfo = PageHelper.startPage(tMember.getPageNum(), tMember.getPageSize()).doSelectPageInfo(() -> tMemberMapper.selectByExample(example));
        return pageInfo;
    }

    @Override
    public TMember selectTMemberById(String id) {
        TMember tMember=new TMember();
        tMember.setId(Integer.valueOf(id));
        return tMemberMapper.selectOne(tMember);
    }

    @Override
    public boolean updateTMember(TMember tMember) {
        return tMemberMapper.updateByPrimaryKeySelective(tMember)>0;
    }
}
