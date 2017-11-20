package cn.gelk.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
}
