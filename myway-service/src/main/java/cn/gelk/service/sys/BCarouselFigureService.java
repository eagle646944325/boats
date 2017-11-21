package cn.gelk.service.sys;

import cn.gelk.domain.BCarouselFigure;
import cn.gelk.domain.TMember;
import com.github.pagehelper.PageInfo;

/**
 * 系统管理
 */
public interface BCarouselFigureService {


    boolean deleteOneTMember(BCarouselFigure BCarouselFigure);

    public PageInfo<BCarouselFigure> listCarouselFigure(BCarouselFigure BCarouselFigure);

    public BCarouselFigure selectBCarouselFigureById(String id);

    boolean updateBCarouselFigure(BCarouselFigure tMember);
    boolean saveBCarouselFigure(BCarouselFigure tMember);
}
