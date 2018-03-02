/*
 * Copyright (C), 2015-2017, zhenhai.xiong
 * FileName: HttpUtil.java
 * Author:   Adan
 * Date:     2017年2月16日 下午1:45:41
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.carejava.duce.ject.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http url 请求工具类<br>
 * http url 请求工具类
 *
 * @author Adan
 * @version [V1.0, 2017年2月16日]
 */
public class HttpUtil {

    private static final Logger LOG = LoggerFactory.getLogger(HttpUtil.class);

    private static final String HTTPCONSTANTS_UTF_8 = "UTF-8";

    /**
     * 
     * 功能描述: <br>
     * get 请求
     *
     * @version [V1.0, 2017年2月16日]
     * @param url
     * @param headers
     * @return
     */
    public static String get(String url, Map<String, String> headers) throws Exception{
        StringBuffer result = new StringBuffer();
        BufferedReader in = null;
        try {
            String urlNameString = url;
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
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),HTTPCONSTANTS_UTF_8));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("发送GET请求出现异常！e={}" , e);
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
        return result.toString();
    }

}
