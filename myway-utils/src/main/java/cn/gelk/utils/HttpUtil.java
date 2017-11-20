package cn.gelk.utils;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * Http操作相关工具类
 * 
 * @author Administrator
 * 
 */
public class HttpUtil {
	private final static String ENCODE = "UTF-8";
    private HttpUtil() {
        
    }
    
    /**
     * URL 解码
     * @return String
     */
    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * URL 转码
     * @return String
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * URL 解码
     * @return String
     */
    public static String getURLDecoderString(String str,String enc) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * URL 转码
     * @return String
     */
    public static String getURLEncoderString(String str,String enc) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 获取Http请求头信息,适用表单方式提交的http请求。
     * @param request
     * @return String
     */
    public static Map<String, String> getHttpParams(HttpServletRequest request) {
        Map<String, String> headerMap = new HashMap<String, String>();
        if (request != null) {
            Enumeration<String> paras = request.getParameterNames();
            while (paras.hasMoreElements()) {
                String paraName = paras.nextElement();
                String paraValue = request.getParameter(paraName);
                headerMap.put(paraName, paraValue);
            }
        }
        return headerMap;
    }
    
    /**
     * 获取Http请求头信息
     * @param request
     * @return String
     */
    public static Map<String, String> getHttpHeaders(HttpServletRequest request) {
        Map<String, String> headerMap = new HashMap<String, String>();
        if (request != null) {
            Enumeration<String> headers = request.getHeaderNames();
            while (headers.hasMoreElements()) {
                String headerName = headers.nextElement();
                String headerValue = request.getHeader(headerName);
                headerMap.put(headerName, headerValue);
            }
        }
        return headerMap;
    }
    
    /**
     * 获取远程主机地址
     * @param request
     * @return String
     */
    public static String getRemoteHost(HttpServletRequest request) {
        String IP = request.getHeader("x-forwarded-for");
        if (IP == null || IP.length() == 0 || "unknown".equalsIgnoreCase(IP)) {
            IP = request.getHeader("Proxy-Client-IP");
        }
        if (IP == null || IP.length() == 0 || "unknown".equalsIgnoreCase(IP)) {
            IP = request.getHeader("WL-Proxy-Client-IP");
        }
        if (IP == null || IP.length() == 0 || "unknown".equalsIgnoreCase(IP)) {
            IP = request.getRemoteAddr();
        }
        if("0:0:0:0:0:0:0:1".equals(IP) 
        		|| "127.0.0.1".equals(IP)){
        	try {
				IP = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				IP = "127.0.0.1";
			}
        }
        return IP;
    }
    
    /**
     * 判断是否ajax请求
     * @param request
     * @return String
     */
    public static boolean isXMLHttpRequest(HttpServletRequest request) {
        String xhr = request.getHeader("X-Requested-With");
        return xhr != null && "XMLHttpRequest".equals(xhr);
    }
    
    public static void main(String[] args) {
		System.out.println(getURLDecoderString("%E4%B8%AD%E5%9B%BD%E7%BA%A2"));
		System.out.println(getURLEncoderString("中国红"));
	}
}
