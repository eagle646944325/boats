package cn.gelk.controller.wutong;

import cn.gelk.bo.WThemeBo;
import cn.gelk.controller.BaseController;
import cn.gelk.domain.TSysUser;
import cn.gelk.domain.WPoetry;
import cn.gelk.domain.WPoetryCollection;
import cn.gelk.domain.WTheme;
import cn.gelk.property.Constants;
import cn.gelk.service.sys.SysService;
import cn.gelk.service.sys.WPoetryCollectionService;
import cn.gelk.service.sys.WPoetryService;
import cn.gelk.service.sys.WThemeService;
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
        wPoetry= wPoetryService.getPoetryInfoById(wPoetry);
        return successResult(wPoetry);
    }

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
        wPoetryCollection.setUserId(Integer.valueOf(userId));
        wPoetryCollectionService.insertWPoetryCollection(wPoetryCollection);
        return successResult();
    }

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



}
