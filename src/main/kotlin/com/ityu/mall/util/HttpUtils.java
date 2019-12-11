package com.ityu.mall.util;

import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author lihe
 */
@Slf4j
@UtilityClass
public class HttpUtils {


    private static final int HTTP_CODE_200 = 200;


    public static String get(String httpUrl) {
        HttpURLConnection connection = null;
        String result = null;
        try {

            // 创建远程url连接对象
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == HTTP_CODE_200) {
                @Cleanup InputStream is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                @Cleanup BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                // 存放数据
                StringBuilder sbf = new StringBuilder();
                String temp;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (Exception e) {
            log.info("request err:", e);
        }finally {
            try {
                if (Objects.nonNull(connection)) {
                    connection.disconnect();
                }
            } catch (Exception ignored) {

            }
        }

        return result;

    }


}
