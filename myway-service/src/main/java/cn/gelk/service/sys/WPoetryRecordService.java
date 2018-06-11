package cn.gelk.service.sys;

import cn.gelk.domain.WPoetryRecord;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

/**
 * 系统管理
 */
public interface WPoetryRecordService {


    WPoetryRecord selectWPoetryRecordBydate(WPoetryRecord wPoetryRecord);

    int insertPoetryRecord(WPoetryRecord wPoetryRecord);

    List<Map> selectWPoetryRecord(WPoetryRecord wPoetryRecord);
}
