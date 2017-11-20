package cn.gelk.controller;

import cn.gelk.domain.TSysUser;
import cn.gelk.property.Constants;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@Controller
public class BaseController {
    
    /**
     * 成功的Status Code.
     */
    private static final int RESULT_CODE_OK = 0;
    /**
     * 成功的 message.
     */
    private static final String OK_MSG = "操作成功";
    
    /**
     * 失败的Status Code.
     */
    private static final int RESULT_CODE_FAIL = 1;
    
    /**
     * 失败的 message.
     */
    private static final String FAIL_MSG = "操作失败";

    public ModelMap returnResult(boolean result){
        if(result){
            return commonResult(RESULT_CODE_OK, OK_MSG);
        }
        else{
            return commonResult(RESULT_CODE_FAIL, FAIL_MSG);
        }
    }

    public ModelMap returnResult(boolean result,String msg){
        if(result){
            if (StringUtils.isEmpty(msg)) { msg =OK_MSG; }
            return commonResult(RESULT_CODE_OK, msg);
        }
        else{
            if (StringUtils.isEmpty(msg)) { msg = FAIL_MSG; }
            return commonResult(RESULT_CODE_FAIL, msg);
        }
    }
    
    public ModelMap successResult() {
        return commonResult(RESULT_CODE_OK, OK_MSG);
    }
    
    public ModelMap successResult(ModelMap model) {
        return commonResult(RESULT_CODE_OK, OK_MSG, model);
    }
    
    public ModelMap successResult(String msg) {
        return commonResult(RESULT_CODE_OK, msg);
    }
    
    public ModelMap successResult(String msg, ModelMap model) {
        return commonResult(RESULT_CODE_OK, msg, model);
    }
    
    public ModelMap successResult(Object obj) {
        return commonResult(RESULT_CODE_OK, OK_MSG, obj);
    }
    
    public ModelMap successResult(String msg, Object obj) {
        return commonResult(RESULT_CODE_OK, msg, obj);
    }
    
    public ModelMap successResult(Object obj, ModelMap model) {
        return commonResult(RESULT_CODE_OK, OK_MSG, obj, model);
    }
    
    public ModelMap failureResult() {
        return commonResult(RESULT_CODE_FAIL, FAIL_MSG);
    }
    
    public ModelMap failureResult(ModelMap model) {
        return commonResult(RESULT_CODE_FAIL, FAIL_MSG, model);
    }
    
    public ModelMap failureResult(String msg) {
        return commonResult(RESULT_CODE_FAIL, msg);
    }
    
    public ModelMap failureResult(String msg, ModelMap model) {
        return commonResult(RESULT_CODE_FAIL, msg, model);
    }
    
    public ModelMap failureResult(Object obj) {
        return commonResult(RESULT_CODE_FAIL, FAIL_MSG, obj);
    }
    
    public ModelMap failureResult(String msg, Object obj) {
        return commonResult(RESULT_CODE_FAIL, msg, obj);
    }
    
    public ModelMap failureResult(Object obj, ModelMap model) {
        return commonResult(RESULT_CODE_FAIL, FAIL_MSG, obj, model);
    }
    
    public RedirectAttributesModelMap failureResult(String msg, RedirectAttributesModelMap model) {
        return commonRedirectResult(RESULT_CODE_FAIL, msg, model);
    }
    
    /**
     * 组装json格式返回结果
     * 
     * @param code
     * @param msg
     * @return
     */
    public ModelMap commonResult(int code, String msg) {
        ModelMap model = new ModelMap();
        model.addAttribute("code", code);
        model.addAttribute("msg", msg);
        model.addAttribute("data", "{}");
        
        return model;
    }
    
    public ModelMap commonResult(int code, String msg, Object obj) {
        ModelMap model = new ModelMap();
        model.addAttribute("code", code);
        model.addAttribute("msg", msg);
        model.addAttribute("data", obj);
        
        return model;
    }
    
    public ModelMap commonResult(int code, String msg, ModelMap model) {
        model.addAttribute("code", code);
        model.addAttribute("msg", msg);
        model.addAttribute("data", "{}");
        
        return model;
    }
    
    public ModelMap commonResult(int code, String msg, Object obj, ModelMap model) {
        model.addAttribute("code", code);
        model.addAttribute("msg", msg);
        model.addAttribute("data", obj);
        
        return model;
    }
    
    public RedirectAttributesModelMap commonRedirectResult(int code, String msg, RedirectAttributesModelMap model) {
        model.addFlashAttribute("code", code);
        model.addFlashAttribute("msg", msg);
        model.addFlashAttribute("data", "{}");
        return model;
    }

    public void setUserToSession(HttpServletRequest request,TSysUser checkUser){
        request.getSession().setAttribute(Constants.USER_SESSION,checkUser);
    }

    public TSysUser getUserFromSession(HttpServletRequest request){
        Object tSysUser = request.getSession().getAttribute(Constants.USER_SESSION);
        if (tSysUser != null){
            return (TSysUser)tSysUser;
        }else{
            return null;
        }
    }

    public boolean isAdmin(HttpServletRequest request){
        TSysUser tSysUser = this.getUserFromSession(request);
        if (tSysUser != null){
            return tSysUser.gettSysRole().getId().equals(1);
        }else {
            return false;
        }
    }


}
