package cn.gelk.exception;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * Spring全局异常处理.
 * 将所有类型的异常处理从各处理过程解耦出来，这样既保证了相关处理过程的功能较单一，也实现了异常信息的统一处理和维护
 */
public class BizExceptionResolver extends SimpleMappingExceptionResolver {
    
    private static Logger logger = LoggerFactory.getLogger(BizExceptionResolver.class);
    private static final int RESULT_CODE_FAIL = 1;
    private static final String ERROR_MSG = "系统异常";
    private static final String ERROR_BZ_MSG = "业务异常";
    private static final String ERROR_SYS_MSG = "系统异常";
    
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 设置Header
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        JSONObject attributes = new JSONObject();
        if (ex instanceof BizException) { // 业务异常
            BizException e = (BizException) ex;
            attributes.put("code", RESULT_CODE_FAIL);
            attributes.put("msg", e.getMessage());
            attributes.put("data", e.getBizExceptionJsonObj());
            logger.error(ERROR_BZ_MSG, ex);
        }
        else { // 系统异常
            response.setStatus(500);
            attributes.put("code", RESULT_CODE_FAIL);
            attributes.put("msg", ERROR_MSG);
            attributes.put("data", ex.getMessage());
            logger.error(ERROR_SYS_MSG, ex);
        }
        
        return new ModelAndView(new FastJsonJsonView(), attributes);
    }
}
