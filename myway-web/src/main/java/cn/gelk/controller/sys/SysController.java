package cn.gelk.controller.sys;

import cn.gelk.controller.BaseController;
import cn.gelk.domain.TSysUser;
import cn.gelk.property.Constants;
import cn.gelk.service.sys.SysService;
import cn.gelk.utils.MD5Util;
import com.github.pagehelper.PageInfo;
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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统管理
 */
@Controller
@RequestMapping("/sys")
public class SysController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SysController.class);

    @Resource
    private SysService sysService;

    /**
     * 登录测试 .
     * RequestBody接收的是一个Json对象 ajax请求的时候要指定dataType: "json",contentType:"application/json"
     * @param tSysUser
     * @return
     */
    @RequestMapping("/login")
    // @ResponseBody 已经采用默认的FastJsonJsonView视图输出了，所以不用springMVC提供的注解ResponseBody方式输出
    public ModelMap login(@RequestBody TSysUser tSysUser,HttpServletResponse response,HttpServletRequest request) {
        if(tSysUser == null ||StringUtils.isEmpty(tSysUser.getUserName()) || StringUtils.isEmpty(tSysUser.getPassword()) ){
            return failureResult("请输入登录用户名和密码");
        }
        // 登录密码MD5设置
        tSysUser.setPassword(MD5Util.encodeByMd5(tSysUser.getPassword()));
        TSysUser checkUser =  sysService.selectOneTSysUser(tSysUser);
        if (checkUser == null){
            return failureResult("登录用户名或输入的密码不正确");
        }

        // 保存session 后期改造分布式session
        checkUser.settSysOrg(sysService.selectTSysOrgById(checkUser.getOrgId()));
        checkUser.settSysRole(sysService.selectTSysRoleById(checkUser.getRoleId()));
        this.setUserToSession(request,checkUser);
        // 屏蔽一些敏感信息
        checkUser.setPassword("******");
        checkUser.setId(-1);
        // 默认的返回视图是 com.alibaba.fastjson.support.spring.FastJsonJsonView
        // 返回json格式的结果
        return successResult(checkUser);
    }

    /**
     * 退出系统 .
     * @param request
     * @return
     */
    @RequestMapping("/sessionuser")
    public ModelMap sessionuser(HttpServletRequest request) {
        TSysUser tSysUser = this.getUserFromSession(request);
        if (tSysUser != null){
            logger.debug("tSysUser = "+tSysUser.toString());
            return successResult(tSysUser);
        }else{
            return failureResult();
        }
    }


    /**
     * 查询用户列表 .
     * @param tSysUser
     * @return
     */
    @RequestMapping("/listuser")
    public ModelMap listuser(TSysUser tSysUser) {
        return successResult(sysService.selectListTSysUser(tSysUser));
    }

    /**
     * 退出系统 .
     * @param request
     * @return
     */
    @RequestMapping("/loginout")
    public ModelMap loginout(HttpServletRequest request) {
        TSysUser tSysUser = this.getUserFromSession(request);
        if (tSysUser != null){
            logger.debug("用户"+((TSysUser)tSysUser).getUserName()+"退出系统");
            request.getSession().removeAttribute(Constants.USER_SESSION);
        }
        return successResult();
    }

}
