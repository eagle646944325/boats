package cn.gelk.service.sys.impl;

import cn.gelk.dao.WPoetryMapper;
import cn.gelk.dao.WPoetryProblemMapper;
import cn.gelk.dao.WPoetryRecordMapper;
import cn.gelk.domain.WPoetry;
import cn.gelk.domain.WPoetryProblem;
import cn.gelk.domain.WPoetryRecord;
import cn.gelk.service.sys.WPoetryProblemService;
import cn.gelk.service.sys.WPoetryRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统管理
 */
@Service
public class WPoetryProblemServiceImpl implements WPoetryProblemService {
    @Resource
    private WPoetryProblemMapper wPoetryProblemMapper;

    @Resource
    private WPoetryMapper wPoetryMapper;


    @Override
    public List<WPoetryProblem> selectByPoetryId(WPoetry wPoetry) {
        wPoetry= wPoetryMapper.selectOne(wPoetry);
        WPoetryProblem wPoetryProblem=new  WPoetryProblem();
        wPoetryProblem.setPoetryId(wPoetry.getOutPoetryId());
        return wPoetryProblemMapper.select(wPoetryProblem);
    }
}
