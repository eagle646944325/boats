package cn.gelk.service.sys.impl;

import cn.gelk.dao.WPoetryRecordMapper;
import cn.gelk.domain.WPoetryRecord;
import cn.gelk.service.sys.WPoetryRecordService;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 系统管理
 */
@Service
public class WPoetryRecordServiceImpl implements WPoetryRecordService {
    @Resource
    private WPoetryRecordMapper wPoetryRecordMapper;

    @Override
    public WPoetryRecord selectWPoetryRecordBydate(WPoetryRecord wPoetryRecord) {
        List<WPoetryRecord> list=wPoetryRecordMapper.selectWPoetryRecordBydate(wPoetryRecord);


        if(list==null||list.size()<=0){
            return null;
        }
        return wPoetryRecordMapper.selectOne(list.get(0));
    }

    @Override
    public int insertPoetryRecord(WPoetryRecord wPoetryRecord) {
        return wPoetryRecordMapper.insert(wPoetryRecord);
    }

    @Override
    public List<Map> selectWPoetryRecord(WPoetryRecord wPoetryRecord) {
        return wPoetryRecordMapper.selectWPoetryRecord(wPoetryRecord);
    }
}
