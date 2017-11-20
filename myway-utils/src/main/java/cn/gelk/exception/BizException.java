package cn.gelk.exception;

import com.alibaba.fastjson.JSONObject;

/**
 * 自定义异常类.
 * 业务异常时可抛出该异常，用于spring事务回滚
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 2249506755801768253L;
    
    private static final String DEFAULT_ERROR_CODE = "FAIL";
    
    private String errorCode;
    
    private String errorMsg;
    
    private JSONObject param;

    public static void create(String errmsg) {
        throw new BizException(errmsg);
    }

    public static void create(String errorCode, String errmsg) {
        throw new BizException(errorCode,errmsg);
    }

    public static void create(String errorCode, String errmsg, JSONObject param) {
        throw new BizException(errorCode,errmsg,param);
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
    
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    
    public JSONObject getParam() {
        return param;
    }
    
    public void setParam(JSONObject param) {
        this.param = param;
    }

    public JSONObject getBizExceptionJsonObj(){
        JSONObject obj = new JSONObject();
        obj.put("errorCode",this.errorCode);
        obj.put("errorMsg",this.errorMsg);
        obj.put("param",this.param == null ? "" : this.param);
        return obj;
    }

    public BizException() {
        super();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
        this.errorCode = DEFAULT_ERROR_CODE;
    }

    public BizException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public BizException(String errorCode, String errorMsg, JSONObject param) {
        super(errorMsg);
        this.errorCode = String.valueOf(errorCode);
        this.param = param;
        this.errorMsg = errorMsg;
    }

    public Throwable fillInStackTrace() {
        return null;
    }
}
