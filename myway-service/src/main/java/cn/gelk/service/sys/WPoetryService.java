package cn.gelk.service.sys;

import cn.gelk.domain.TSysOrg;
import cn.gelk.domain.TSysRole;
import cn.gelk.domain.TSysUser;
import cn.gelk.domain.WPoetry;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 系统管理
 */
public interface WPoetryService {


    WPoetry getPoetryInfoById(WPoetry wPoetry);

    List<WPoetry> getPoetryAll();

    List<WPoetry> selectPoetryByThemeId(String themeId);
}
