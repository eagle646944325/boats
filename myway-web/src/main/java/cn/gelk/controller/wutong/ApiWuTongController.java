package cn.gelk.controller.wutong;

import cn.gelk.bo.WThemeBo;
import cn.gelk.controller.BaseController;
import cn.gelk.domain.*;
import cn.gelk.property.Constants;
import cn.gelk.service.sys.*;
import cn.gelk.utils.AesCbcUtil;
import cn.gelk.utils.HttpClientUtils;
import cn.gelk.utils.MD5Util;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.xfire.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;
import java.util.*;


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


    /****************************************************微信小程序获取登陆信息 start********************************/
    @RequestMapping("/wx/getSessionKeyOropenid")
    @ResponseBody
    public ModelMap getSessionKeyOropenid(String code,String wxspAppid,String wxspSecret) {
        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            return failureResult("code 不能为空");
        }

        //小程序唯一标识   (在微信小程序管理后台获取)
//        String wxspAppid = "xxxxxxxxxxxxxx";
//        //小程序的 app secret (在微信小程序管理后台获取)
//        String wxspSecret = "xxxxxxxxxxxxxx";
        //授权（必填）
        String grant_type = "authorization_code";


        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        //请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String sr = HttpClientUtils.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        return successResult(sr);
//        //解析相应内容（转换成json对象）
//        JSONObject json = JSONObject.parseObject(sr);
//        //获取会话密钥（session_key）
//        String session_key = json.get("session_key").toString();
//        //用户的唯一标识（openid）
//        String openid = (String) json.get("openid");


    }
    /**
     * 解密用户敏感数据
     *
     * @param encryptedData 明文,加密数据
     * @param iv            加密算法的初始向量
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/wx/decodeUserInfo", method = RequestMethod.POST)
    public ModelMap decodeUserInfo(String encryptedData, String iv,  String session_key ) {
//
//        Map map = new HashMap();

        //登录凭证不能为空
//        if (code == null || code.length() == 0) {
//            map.put("status", 0);
//            map.put("msg", "code 不能为空");
//            return map;
//        }

        //小程序唯一标识   (在微信小程序管理后台获取)
//        String wxspAppid = "xxxxxxxxxxxxxx";
//        //小程序的 app secret (在微信小程序管理后台获取)
//        String wxspSecret = "xxxxxxxxxxxxxx";
        //授权（必填）
//        String grant_type = "authorization_code";


        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        //请求参数
//        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
//        //发送请求
//        String sr = HttpClientUtils.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
//        //解析相应内容（转换成json对象）
//        JSONObject json = JSONObject.parseObject(sr);
//        //获取会话密钥（session_key）
//        String session_key = json.get("session_key").toString();
//        //用户的唯一标识（openid）
//        String openid = (String) json.get("openid");

        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        try {
            String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            if (null != result && result.length() > 0) {
//                map.put("status", 1);
//                map.put("msg", "解密成功");
                return successResult(result);
//                JSONObject userInfoJSON = JSONObject.parseObject(result);
//                Map userInfo = new HashMap();
//                userInfo.put("openId", userInfoJSON.get("openId"));
//                userInfo.put("nickName", userInfoJSON.get("nickName"));
//                userInfo.put("gender", userInfoJSON.get("gender"));
//                userInfo.put("city", userInfoJSON.get("city"));
//                userInfo.put("province", userInfoJSON.get("province"));
//                userInfo.put("country", userInfoJSON.get("country"));
//                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
//                userInfo.put("unionId", userInfoJSON.get("unionId"));
//                map.put("userInfo", userInfo);
//                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        map.put("status", 0);
//        map.put("msg", "解密失败");
        return failureResult("解密失败");
    }

    /****************************************************微信小程序获取登陆信息 end********************************/

}
