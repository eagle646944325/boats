package cn.gelk.controller.wutong;

import cn.gelk.controller.BaseController;
import cn.gelk.domain.TSysUser;
import cn.gelk.domain.WPoetry;
import cn.gelk.domain.WPoetryCollection;
import cn.gelk.property.Constants;
import cn.gelk.service.sys.SysService;
import cn.gelk.service.sys.WPoetryCollectionService;
import cn.gelk.service.sys.WPoetryService;
import cn.gelk.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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


}
