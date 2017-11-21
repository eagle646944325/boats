package cn.gelk.controller.sys;

import cn.gelk.controller.BaseController;
import cn.gelk.domain.BLeavingMessage;
import cn.gelk.domain.TMember;
import cn.gelk.service.sys.BLeavingMessageService;
import cn.gelk.service.sys.MemberService;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统管理
 */
@Controller
@RequestMapping("/leavingMessage")
public class BLeavingMessageController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(BLeavingMessageController.class);

    @Resource
    private BLeavingMessageService bLeavingMessageService;



    @RequestMapping("/listLeavingMessage")
    public ModelMap listuser(BLeavingMessage leavingMessag) {
        return successResult(bLeavingMessageService.selectListBLeavingMessage(leavingMessag));
    }


    @RequestMapping("/findLeavingMessage")
    public ModelMap  findMember(String id) {
        return successResult(bLeavingMessageService.selectBLeavingMessageById(id));
    }

    @RequestMapping("/delLeavingMessage")
    public ModelMap  delLeavingMessage(BLeavingMessage leavingMessag) {
        return successResult(bLeavingMessageService.deleteOneTMembere(leavingMessag));
    }
    @RequestMapping("/saveMessage")
    public ModelMap  saveMessage(BLeavingMessage leavingMessag) {
        return successResult(bLeavingMessageService.insertBLeavingMessage(leavingMessag));
    }


}
