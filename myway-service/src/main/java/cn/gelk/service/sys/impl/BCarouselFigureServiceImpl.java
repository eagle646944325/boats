package cn.gelk.service.sys.impl;

import cn.gelk.dao.BCarouselFigureMapper;
import cn.gelk.dao.BLeavingMessageMapper;
import cn.gelk.domain.BCarouselFigure;
import cn.gelk.domain.BLeavingMessage;
import cn.gelk.service.sys.BCarouselFigureService;
import cn.gelk.service.sys.BLeavingMessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class BCarouselFigureServiceImpl implements BCarouselFigureService {

    @Resource
    private BCarouselFigureMapper bCarouselFigureMapper;


    @Override
    public boolean deleteOneTMember(BCarouselFigure bCarouselFigure) {
        return bCarouselFigureMapper.delete(bCarouselFigure)>0;
    }

    @Override
    public PageInfo<BCarouselFigure> listCarouselFigure(BCarouselFigure bCarouselFigure) {
        return null;
    }

    @Override
    public BCarouselFigure selectBCarouselFigureById(String id) {
        BCarouselFigure bCarouselFigure=new BCarouselFigure();
        bCarouselFigure.setId(Integer.valueOf(id));
        return bCarouselFigureMapper.selectOne(bCarouselFigure);
    }

    @Override
    public boolean updateBCarouselFigure(BCarouselFigure bCarouselFigure) {
        return bCarouselFigureMapper.updateByPrimaryKeySelective(bCarouselFigure)>0;
    }

    @Override
    public boolean saveBCarouselFigure(BCarouselFigure tMember) {
         return bCarouselFigureMapper.insert(tMember)>0;
    }
}
