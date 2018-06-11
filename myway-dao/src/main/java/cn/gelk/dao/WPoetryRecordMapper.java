package cn.gelk.dao;


import cn.gelk.domain.TSysUser;
import cn.gelk.domain.WPoetryRecord;
import cn.gelk.markerInterface.MyBatisMapper;

import java.util.List;
import java.util.Map;

public interface WPoetryRecordMapper extends MyBatisMapper<WPoetryRecord> {
    List<WPoetryRecord> selectWPoetryRecordBydate(WPoetryRecord wPoetryRecord);

    List<Map> selectWPoetryRecord(WPoetryRecord wPoetryRecord);
}