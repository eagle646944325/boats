package cn.gelk.controller.wutong;

import cn.gelk.bo.WThemeBo;
import cn.gelk.controller.BaseController;
import cn.gelk.domain.*;
import cn.gelk.property.Constants;
import cn.gelk.service.sys.*;
import cn.gelk.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统管理
 */
@Controller
@RequestMapping("/api")
public class ApiWuTongController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ApiWuTongController.class);

    @Resource
    private WPoetryService wPoetryService;

    @Resource
    private WPoetryCollectionService wPoetryCollectionService;
    @Resource
    private WThemeService wThemeService;
    @Resource
    private WUserService wUserService;

    /**
     * 1、请求诗信息信息（首页或者详情页）
     * @param poetryId
     * @param userId
     * @return
     */
    @RequestMapping("/poetry/getInfo")
    @ResponseBody
    public ModelMap getInfo(String  poetryId,String userId) {

        WPoetry wPoetry=new WPoetry();
        wPoetry.setId(Integer.valueOf(poetryId));
        try{
            wPoetry= wPoetryService.getPoetryInfoById(wPoetry);
        }catch(Exception e){
            return failureResult("古诗不存在");
        }

        return successResult(wPoetry);
    }
/*****************************************收藏列表API接口**************************************************************************/
    /**
     * 收藏
     * @param poetryId
     * @param userId
     * @return
     */
    @RequestMapping("/poetry/collection")
    @ResponseBody
    public ModelMap collection(String  poetryId,String userId) {
        WPoetryCollection wPoetryCollection=new WPoetryCollection();
        wPoetryCollection.setPoetryId(Integer.valueOf(poetryId));
        wPoetryCollection.setAccountNo(userId);
        wPoetryCollection.setCreateTime(new Date());
        wPoetryCollectionService.insertWPoetryCollection(wPoetryCollection);
        return successResult();
    }

    /**
     * 取消收藏
     * @param poetryId
     * @param userId
     * @return
     */
    @RequestMapping("/poetry/cancelCollection")
    @ResponseBody
    public ModelMap cancelCollection(String  poetryId,String userId) {
        WPoetryCollection wPoetryCollection=new WPoetryCollection();
        wPoetryCollection.setPoetryId(Integer.valueOf(poetryId));
        wPoetryCollection.setAccountNo(userId);
        wPoetryCollectionService.deleteCollection(wPoetryCollection);
        return successResult();
    }


    /**
     * 收藏列表
     * @param userId
     * @return
     */
    @RequestMapping("/poetry/selectCollection")
    @ResponseBody
    public ModelMap selectCollection(String userId) {
        WPoetryCollection wPoetryCollection=new WPoetryCollection();
        wPoetryCollection.setAccountNo(userId);
        List<WPoetry> list=wPoetryCollectionService.selectCollection(wPoetryCollection);
        return successResult(list);
    }
/*****************************************收藏列表API接口**************************************************************************/
    /**
     * 4、全部诗词
     * @return
     */
    @RequestMapping("/poetry/all")
    @ResponseBody
    public ModelMap getPoetryAll() {
    List<WPoetry> wPoetry= wPoetryService.getPoetryAll();
        return successResult(wPoetry);
    }
/*****************************************主题API接口**************************************************************************/
    /**
     * 4，全部诗词-主题
     * @return
     */
    @RequestMapping("/theme/all")
    @ResponseBody
    public ModelMap getThemeAll() {
        WTheme wTheme=new WTheme();
        wTheme.setType("0");
        List<WTheme> wThemeList= wThemeService.selectThemeByParam(wTheme);
        List<WThemeBo> returnList=new ArrayList<WThemeBo>();
            for(int i=0;i<wThemeList.size();i++){
                WThemeBo wThemeBo=new WThemeBo();
                BeanUtils.copyProperties(wThemeList.get(i),wThemeBo);
                WTheme wTheme2=new WTheme();
                wTheme2.setParentId(wThemeList.get(i).getId());
                wTheme2.setType("1");
                List<WTheme> wThemeList2= wThemeService.selectThemeByParam(wTheme2);
                wThemeBo.setSubWTheme(wThemeList2);
                returnList.add(wThemeBo);
            }
        return successResult(returnList);
    }


    /**
     * 4，全部诗词-主题-查询主题信息
     * @return
     */
    @RequestMapping("/theme/info")
    @ResponseBody
    public ModelMap getThemeInfo(String userId,String themeId) {
        WTheme wTheme=new WTheme();
        wTheme.setType("1");
        wTheme.setId(Integer.valueOf(themeId));
        List<WTheme> wThemeList= wThemeService.selectThemeByParam(wTheme);
        WThemeBo wThemeBo=new WThemeBo();
        if(wThemeList.size()<=0){
            return successResult(wThemeBo);
        }else{
            BeanUtils.copyProperties(wThemeList.get(0),wThemeBo);
        }
        return successResult(wThemeBo);
    }

    /**
     * 4，全部诗词-主题-查询主题信息
     * @return
     */
    @RequestMapping("/theme/poetry")
    @ResponseBody
    public ModelMap getThemePoetry(String userId,String themeId) {
     List<WPoetry> list=wPoetryService.selectPoetryByThemeId(themeId);
        return successResult(list);
    }

/*****************************************主题API接口**************************************************************************/













    /***************************用户接口*******************************************************************/
    /**
     * 9，上传用户信息
     * @return
     */
    @RequestMapping("/user/upload")
    @ResponseBody
    public ModelMap userUpload(String userId,String userName) {
        WUser wUser=new WUser();
        wUser.setAccountNo(userId);
        WUser wUser1=  wUserService.selectWuser(wUser);
        if(wUser1!=null){
            wUser1.setName(userName);
            wUserService.updateWuser(wUser1);
        }else{
            wUser.setName(userName);
            wUserService.saveWuser(wUser);
        }
        return successResult();
    }


    /**
     * 用户选择难度
     * @return
     */
    @RequestMapping("/user/difficulty")
    @ResponseBody
    public ModelMap userDifficulty(String userId,String difficulty) {
        WUser wUser=new WUser();
        wUser.setAccountNo(userId);
        WUser wUser1=  wUserService.selectWuser(wUser);
        if(wUser1!=null){
            wUser1.setDifficulty(difficulty);
            wUserService.updateWuser(wUser1);
        }else{
            return failureResult("用户不存在");
        }
        return successResult();
    }

    /**
     * 用户读取天数+1
     * @return
     */
    @RequestMapping("/user/readDay")
    @ResponseBody
    public ModelMap userreadDay(String userId) {
        WUser wUser=new WUser();
        wUser.setAccountNo(userId);
        WUser wUser1=  wUserService.selectWuser(wUser);
        if(wUser1!=null){
            if(StringUtils.isNotEmpty(wUser1.getReadDays()+"")){
                wUser1.setReadDays(wUser1.getReadDays()+1);
            }else{
                wUser1.setReadDays(1);
            }

            wUserService.updateWuser(wUser1);
        }else{
            return failureResult("用户不存在");
        }
        return successResult();
    }


    /**
     * 9，查询用户信息
     * @return
     */
    @RequestMapping("/user/info")
    @ResponseBody
    public ModelMap userInfo(String userId) {
        WUser wUser=new WUser();
        wUser.setAccountNo(userId);
        WUser wUser1=  wUserService.selectWuser(wUser);
        return successResult(wUser1);
    }
    /***************************用户接口*******************************************************************/

}
