package cn.gelk.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.*;
public class HttpClientUtils {

    /**
     * 关闭httpclient对象.
     * @param httpResponse
     * @param httpClient
     * @throws IOException
     */
    public static void closeHttpCLinet(CloseableHttpResponse httpResponse,CloseableHttpClient httpClient) throws IOException {
        if (httpResponse != null) {
            httpResponse.close();
            httpResponse = null;
        }
        if (httpClient != null) {
            httpClient.close();
            httpClient = null;
        }
    }
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 发送post的请求, 请求接口参数需要添加@RequestBody.
     * @param url 地址
     * @param params 参数在请求头中
     * @return json字符串
     * @throws Exception
     */
    public static String doPostEntity(String url, final String params) throws IOException {
        StringBuffer buffer = new StringBuffer();
        CloseableHttpResponse httpResponse = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        ContentProducer cp = new ContentProducer() {
            public void writeTo(OutputStream outstream) throws IOException {
                Writer writer = new OutputStreamWriter(outstream, "UTF-8");
                writer.write(params);
                writer.flush();
            }
        };
        HttpEntity entity = new EntityTemplate(cp);
        httpPost.setEntity(entity);
        httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        buffer.append(EntityUtils.toString(httpEntity, "UTF-8"));
        closeHttpCLinet(httpResponse,httpClient);
        return buffer.toString();
    }

    /**
     * 发送post的请求，模拟Form提交参数.
     * @param url 地址
     * @param params 参数在请求头中
     * @return json字符串
     */
    public static String doPost(String url, final String params) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        return doPost(url, params, httpClient);
    }


    /**
     * 发送post的请求，模拟Form提交参数.
     * 设置超时时间.
     * @param socketTimeout 单位秒 从服务器读取数据的timeout
     * @param connectTimeout 单位秒 和服务器建立连接的timeout
     * @return json字符串
     */
    public static String doPost(String url, final String params, int socketTimeout, int connectTimeout) throws IOException {
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout * 1000).setConnectTimeout(connectTimeout * 1000).build();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
        return doPost(url, params, httpClient);
    }

    /**
     * 发送post的请求，模拟Form提交参数.
     * @return json字符串
     * @throws Exception
     */
    public static String doPost(String url, final String params, CloseableHttpClient httpClient) throws IOException {
        StringBuffer buffer = new StringBuffer();
        CloseableHttpResponse httpResponse = null;
        HttpPost httpPost = new HttpPost(url);
        // httpClient = HttpClients.createDefault();
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        if (StringUtils.trimToNull(params) != null) {
            JSONObject json = JSON.parseObject(params);
            for (String key : json.keySet()) {
                param.add(new BasicNameValuePair(key, json.getString(key)));
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(param, "UTF-8"));
        httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        buffer.append(EntityUtils.toString(httpEntity, "UTF-8"));
        closeHttpCLinet(httpResponse,httpClient);
        return buffer.toString();
    }

    /**
     * 发送get，模拟Form提交参数.
     * @param url
     * @param json
     * @return json字符串
     */
    public static String doGet(String url, String json) throws IOException {
        StringBuilder buffer = new StringBuilder();
        if (json != null) {
            JSONObject obj = JSON.parseObject(json);
            for (String key : obj.keySet()) {
                if (buffer.length() == 0) {
                    buffer.append(key).append("=").append(obj.getString(key));
                }
                else {
                    buffer.append("&").append(key).append("=").append(obj.getString(key));
                }
            }
            if (url.indexOf("?") == -1) {
                url += "?" + buffer.toString();
            }
            else {
                url += "&" + buffer.toString();
            }
            buffer = new StringBuilder();
        }
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        buffer.append(EntityUtils.toString(httpEntity, "UTF-8"));
        closeHttpCLinet(httpResponse,httpClient);
        return buffer.toString();
    }

    public static String sendPost(String url, Map<String, ?> paramMap) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        String param = "";
        Iterator<String> it = paramMap.keySet().iterator();

        while(it.hasNext()) {
            String key = it.next();
            param += key + "=" + paramMap.get(key) + "&";
        }
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.toString();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }


}
