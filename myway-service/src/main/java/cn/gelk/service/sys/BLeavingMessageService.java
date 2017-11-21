package cn.gelk.service.sys;

import cn.gelk.domain.BLeavingMessage;
import com.github.pagehelper.PageInfo;

/**
 * 系统管理
 */
public interface BLeavingMessageService {


    boolean deleteOneTMembere(BLeavingMessage leavingMessag);
    public PageInfo<BLeavingMessage> selectListBLeavingMessage(BLeavingMessage leavingMessag);
    public BLeavingMessage selectBLeavingMessageById(String id);
    boolean updateBLeavingMessage(BLeavingMessage leavingMessag);
    boolean insertBLeavingMessage(BLeavingMessage leavingMessag);

}
