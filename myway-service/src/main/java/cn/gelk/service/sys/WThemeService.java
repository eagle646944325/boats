package cn.gelk.service.sys;

import cn.gelk.domain.WPoetryCollection;
import cn.gelk.domain.WTheme;

import java.util.List;

/**
 * 系统管理
 */
public interface WThemeService {


    List<WTheme> selectThemeByParam(WTheme wTheme);
}
