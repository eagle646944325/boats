package cn.gelk.markerInterface;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface MyBatisMapper<T> extends MySqlMapper<T>, Mapper<T> {
    
}
