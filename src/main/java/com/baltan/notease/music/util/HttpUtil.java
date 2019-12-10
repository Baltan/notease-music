package com.baltan.notease.music.util;

import com.baltan.notease.music.config.HttpConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;
import java.net.*;
import java.util.Map;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-06 16:47
 */
@Component
public class HttpUtil {
    private static volatile HttpConfig httpConfig;

    /**
     * 无法实例化工具类
     */
    private HttpUtil() {
    }

    /**
     * 表单提交POST请求
     *
     * @param paramsMap
     * @throws IOException
     */
    public static String post(Map<String, String> paramsMap, String url) {
        StringBuilder result = new StringBuilder();
        OutputStreamWriter osw = null;
        BufferedReader br = null;

        try {
            URL requestUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod(RequestMethod.POST.name());
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", httpConfig.getContentTypeForm());

            osw = new OutputStreamWriter(connection.getOutputStream(), httpConfig.getCharset());
            osw.write(createParameter(paramsMap));
            osw.flush();

            br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), httpConfig.getCharset()));
            String temp;

            while ((temp = br.readLine()) != null) {
                result.append(temp);
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result.toString();
    }

    /**
     * 将map中的键值对拼接成url中的请求参数
     * 例如：输入{"k1":"v1","k2":"v2"}，输出k1=v1&k2=v2
     *
     * @param paramsMap
     * @return
     * @throws Exception
     */
    private static String createParameter(Map<String, String> paramsMap)
            throws Exception {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            builder.append(entry.getKey());
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue(), httpConfig.getCharset()));
            builder.append("&");
        }
        return builder.substring(0, builder.length() - 1);
    }

    @Autowired
    public void setHttpConfig(HttpConfig httpConfig) {
        HttpUtil.httpConfig = httpConfig;
    }
}
