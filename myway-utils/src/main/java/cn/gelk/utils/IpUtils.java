package cn.gelk.utils;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class IpUtils {


	/**
	 * 通过request对象获取对应的IP地址.
	 * @param request
	 * @return String
	 */
    public static String getRequestIP(HttpServletRequest request)
    {
        String requestIP = request.getHeader("x-forwarded-for");
        if (requestIP == null || requestIP.length() == 0
                || "unknown".equalsIgnoreCase(requestIP))
        {
            requestIP = request.getHeader("Proxy-Client-IP");
        }
        if (requestIP == null || requestIP.length() == 0
                || "unknown".equalsIgnoreCase(requestIP))
        {
            requestIP = request.getHeader("WL-Proxy-Client-IP");
        }
        if (requestIP == null || requestIP.length() == 0
                || "unknown".equalsIgnoreCase(requestIP))
        {
            requestIP = request.getRemoteAddr();
        }
        if (requestIP != null && requestIP.length() > 15)
        {
            if (requestIP.indexOf(",") > 0)
            {
                requestIP = requestIP.substring(0, requestIP.indexOf(","));
            }
        }
        
        return requestIP.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : requestIP;
    }
    
    /**
     * 获取本机的IP地址信息.
     * @return String
     */
    public static Map<String,String> getLocalIp( ){
    	InetAddress addr;
    	String ip = "";
    	String hostName = "";
    	Map<String,String> map = new HashMap<String, String>();
		try {
			addr = InetAddress.getLocalHost();
			ip=addr.getHostAddress();	//获得本机IP
	    	hostName=addr.getHostName();	//获得本机名称
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		map.put("hostName", hostName);
		map.put("ip", ip);
    	return map;
    }
    
	/**
	 * 根据主机名得到IP地址字符串.
	 * 
	 * @param hostName 要查找地址的主机名
	 * @return 对应主机的IP地址，主机名未知或者非法时返回INVALID_IP。
	 */
	public static String getByName(String hostName) {
		try {
			InetAddress inet = InetAddress.getByName(hostName);
			return inet.getHostAddress();
		} catch (UnknownHostException e) {
			return "";
		}
	}

	/**
	 * 根据IP地址得到主机名.
	 * 
	 * @param ip 要查找主界面的IP地址
	 * @return 对应IP的主机名，IP地址未知时返回UNKNOWN_HOST，IP地址未知也可能是网络问题造成的。
	 */
	public static String getHostName(String ip) {
		try {
			InetAddress inet = InetAddress.getByName(ip);
			return inet.getHostName();
		} catch (UnknownHostException e) {
			return "";
		}
	}
    
	/**
	 * 获取本机mac
	 * @return String
	 */
    private static String getLocalMac(){
        try {
            InetAddress ia = InetAddress.getLocalHost();
            //获取网卡，获取地址
            byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
            System.out.println("mac数组长度："+mac.length);
            StringBuffer sb = new StringBuffer("");
            for(int i=0; i<mac.length; i++) {
                if(i!=0) {
                    sb.append("-");
                }
                //字节转换为整数
                int temp = mac[i]&0xff;
                String str = Integer.toHexString(temp);
                System.out.println("每8位:"+str);
                if(str.length()==1) {
                    sb.append("0"+str);
                }else {
                    sb.append(str);
                }
            }
            System.out.println("本机MAC地址:"+sb.toString().toUpperCase());
            
            return sb.toString().toUpperCase();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

	/**
	 * 判断是否ajax请求
	 *
	 * @param request
	 * @return String
	 */
	public static boolean isXMLHttpRequest(HttpServletRequest request) {
		String xhr = request.getHeader("X-Requested-With");
		return xhr != null && "XMLHttpRequest".equals(xhr);
	}

	/**
	 * 获取操作系统类型
	 * @param request
	 * @return String
	 */
	public static String getRequestOS(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		String os = "";
		// =================OS Info=======================
		if (userAgent.toLowerCase().indexOf("windows") >= 0) {
			os = "Windows";
		}
		else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
			os = "IPhone";
		}
		else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
			os = "Mac";
		}
		else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
			os = "Unix";
		}
		else if (userAgent.toLowerCase().indexOf("android") >= 0) {
			os = "Android";
		}
		else {
			os = "UnKnown";
		}

		return os;
	}

    public static void main(String[] args) {
		System.out.println(getLocalIp().get("ip"));
		getLocalMac();
	}
}
