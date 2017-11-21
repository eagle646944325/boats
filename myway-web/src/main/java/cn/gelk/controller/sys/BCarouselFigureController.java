package cn.gelk.controller.sys;

import cn.gelk.controller.BaseController;
import cn.gelk.domain.BCarouselFigure;
import cn.gelk.domain.BLeavingMessage;
import cn.gelk.service.sys.BCarouselFigureService;
import cn.gelk.service.sys.BLeavingMessageService;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统管理
 */
@Controller
@RequestMapping("/carouselFigure")
public class BCarouselFigureController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(BCarouselFigureController.class);

    @Resource
    private BCarouselFigureService bCarouselFigureService;

    @RequestMapping("/listCarouselFigure")
    public ModelMap listCarouselFigure(BCarouselFigure bCarouselFigure) {
        return successResult(bCarouselFigureService.listCarouselFigure(bCarouselFigure));
    }
    @RequestMapping("/findCarouselFigure")
    public ModelMap  findCarouselFigure(String id) {
        return successResult(bCarouselFigureService.selectBCarouselFigureById(id));
    }
    @RequestMapping("/delCarouselFigure")
    public ModelMap  delCarouselFigure(BCarouselFigure bCarouselFigure) {
        return successResult(bCarouselFigureService.deleteOneTMember(bCarouselFigure));
    }
    @RequestMapping("/saveCarouselFigure")
    public ModelMap  saveCarouselFigure(BCarouselFigure bCarouselFigure) {
        return successResult(bCarouselFigureService.saveBCarouselFigure(bCarouselFigure));
    }


}
