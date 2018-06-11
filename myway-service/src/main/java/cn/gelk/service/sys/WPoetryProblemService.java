package cn.gelk.service.sys;


import cn.gelk.domain.WPoetry;
import cn.gelk.domain.WPoetryProblem;

import java.util.List;

/**
 * 系统管理
 */
public interface WPoetryProblemService {


    List<WPoetryProblem> selectByPoetryId(WPoetry wPoetry);
}
