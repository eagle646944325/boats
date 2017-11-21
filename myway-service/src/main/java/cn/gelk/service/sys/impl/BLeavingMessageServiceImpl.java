package cn.gelk.service.sys.impl;

import cn.gelk.dao.BLeavingMessageMapper;
import cn.gelk.dao.TMemberMapper;
import cn.gelk.domain.BLeavingMessage;
import cn.gelk.domain.TMember;
import cn.gelk.service.sys.BLeavingMessageService;
import cn.gelk.service.sys.MemberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class BLeavingMessageServiceImpl implements BLeavingMessageService {

    @Resource
    private BLeavingMessageMapper bLeavingMessageMapper;

    @Override
    public boolean deleteOneTMembere(BLeavingMessage leavingMessag) {
        return bLeavingMessageMapper.delete(leavingMessag)>0;
    }

    @Override
    public PageInfo<BLeavingMessage> selectListBLeavingMessage(BLeavingMessage leavingMessag) {
            PageInfo<BLeavingMessage> pageInfo = null;
            Example example = new Example(BLeavingMessage.class);
            Example.Criteria criteria = example.createCriteria();
            if (!StringUtils.isEmpty(leavingMessag.getLeaveUserName())){
                criteria.andLike("leave_user_name",leavingMessag.getLeaveUserName());
            }
            example.orderBy("id").desc();
            pageInfo = PageHelper.startPage(leavingMessag.getPageNum(), leavingMessag.getPageSize()).doSelectPageInfo(() -> bLeavingMessageMapper.selectByExample(example));
            return pageInfo;
    }

    @Override
    public BLeavingMessage selectBLeavingMessageById(String id) {
        BLeavingMessage leavingMessag=new BLeavingMessage();
        leavingMessag.setId(Integer.valueOf(id));
        return bLeavingMessageMapper.selectOne(leavingMessag);
    }

    @Override
    public boolean updateBLeavingMessage(BLeavingMessage leavingMessag) {
        return bLeavingMessageMapper.updateByPrimaryKeySelective(leavingMessag)>0;
    }

    @Override
    public boolean insertBLeavingMessage(BLeavingMessage leavingMessag) {
        return bLeavingMessageMapper.insert(leavingMessag)>0;
    }

}
