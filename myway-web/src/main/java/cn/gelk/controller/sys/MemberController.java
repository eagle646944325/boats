package cn.gelk.controller.sys;

import cn.gelk.controller.BaseController;
import cn.gelk.domain.TMember;
import cn.gelk.domain.TSysUser;
import cn.gelk.property.Constants;
import cn.gelk.service.sys.MemberService;
import cn.gelk.service.sys.SysService;
import cn.gelk.utils.MD5Util;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统管理
 */
@Controller
@RequestMapping("/member")
public class MemberController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Resource
    private MemberService memberService;



    /**
     * 查询用户列表 .
     * @param tMember
     * @return
     */
    @RequestMapping("/listMember")
    public ModelMap listuser(TMember tMember) {
        return successResult(memberService.selectListTMember(tMember));
    }

    /**
     * 查询用户列表 .
     * @param id
     * @return
     */
    @RequestMapping("/findMember")
    public ModelMap  findMember(String id) {

        
        return successResult(memberService.selectTMemberById(id));
    }


    @RequestMapping("/editMember")
    public ModelMap  editMember(TMember tMember) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);




        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
        if (!StringUtils.isEmpty(tMember.getSearchStartTime())){
            Date strtodate = formatter.parse(tMember.getSearchStartTime(), pos);
            tMember.setStartTime(strtodate);

        }
        if (!StringUtils.isEmpty(tMember.getSearchEndTime())){
            Date enddate = formatter.parse(tMember.getSearchEndTime(), pos);
            tMember.setEndTime(enddate);

        }
       return successResult(memberService.updateTMember(tMember));
    }

}
