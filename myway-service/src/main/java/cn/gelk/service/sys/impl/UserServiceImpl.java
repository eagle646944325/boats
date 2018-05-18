package cn.gelk.service.sys.impl;

import cn.gelk.dao.TSysUserMapper;
import cn.gelk.domain.TSysUser;
import cn.gelk.service.sys.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {

    /**
     *
     */
    @Resource
    private TSysUserMapper tSysUserMapper;

    @Override
    public boolean saveOneTSysUser(TSysUser tSysUserSysUser) {
        return tSysUserMapper.insert(tSysUserSysUser) > 0;
    }

    @Override
    public boolean deleteOneTSysUser(TSysUser tSysUserSysUser) {
        return tSysUserMapper.delete(tSysUserSysUser) > 0;
    }

    @Override
    public boolean updateOneTSysUser(TSysUser tSysUserSysUser) {
        return tSysUserMapper.updateByPrimaryKey(tSysUserSysUser) > 0;
    }

    @Override
    public TSysUser selectOneTSysUser(TSysUser tSysUserSysUser) {
        return tSysUserMapper.selectOne(tSysUserSysUser);
    }

    @Override
    public PageInfo<TSysUser> selectListTSysUser(TSysUser tSysUserSysUser) {
        PageInfo<TSysUser> pageInfo = null;
        Example example = new Example(TSysUser.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(tSysUserSysUser.getUserName())){
            criteria.andEqualTo("userName",tSysUserSysUser.getUserName());
        }
        if (!StringUtils.isEmpty(tSysUserSysUser.getMsisdn())){
            criteria.andEqualTo("msisdn",tSysUserSysUser.getMsisdn());
        }
        if (!StringUtils.isEmpty(tSysUserSysUser.getQq())){
            criteria.andEqualTo("qq",tSysUserSysUser.getQq());
        }
        if (!StringUtils.isEmpty(tSysUserSysUser.getWx())){
            criteria.andEqualTo("wx",tSysUserSysUser.getWx());
        }
        if (!StringUtils.isEmpty(tSysUserSysUser.getOrgId())){
            criteria.andEqualTo("orgId",tSysUserSysUser.getOrgId());
        }
        if (!StringUtils.isEmpty(tSysUserSysUser.getRoleId())){
            criteria.andEqualTo("roleId",tSysUserSysUser.getRoleId());
        }
        if (tSysUserSysUser.getSex() != null){
            criteria.andEqualTo("sex",tSysUserSysUser.getSex());
        }
        if (tSysUserSysUser.getStatus() != null){
            criteria.andEqualTo("status",tSysUserSysUser.getStatus());
        }
        if (!StringUtils.isEmpty(tSysUserSysUser.getSearchStartTime())){
            criteria.andGreaterThan("createTime",tSysUserSysUser.getSearchStartTime());
        }
        if (!StringUtils.isEmpty(tSysUserSysUser.getSearchEndTime())){
            criteria.andLessThan("createTime",tSysUserSysUser.getSearchEndTime());
        }
        example.orderBy("id").desc();
        pageInfo = PageHelper.startPage(tSysUserSysUser.getPageNum(), tSysUserSysUser.getPageSize()).doSelectPageInfo(() -> tSysUserMapper.selectByExample(example));
        return pageInfo;
    }

}
