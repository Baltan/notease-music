package com.baltan.notease.music;

import com.alibaba.fastjson.JSON;
import com.baltan.notease.music.config.NeteaseConfig;
import com.baltan.notease.music.util.EncryptUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteaseMusicApplicationTests {
    @Autowired
    private NeteaseConfig neteaseConfig;

    @Test
    public void testNeteaseConfig() {
        System.out.println(neteaseConfig);
    }

    @Test
    public void testEncryptUtil() throws Exception {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("hlpretag", "<span class=\\\"s-fc7\\\">");
        requestParams.put("hlposttag", "</span>");
        requestParams.put("#/discover", "");
        requestParams.put("s", "李荣浩");
        requestParams.put("type", "1");
        requestParams.put("offset", "0");
        requestParams.put("total", "true");
        requestParams.put("limit", "30");
        requestParams.put("csrf_token", "");
        String cipherText = JSON.toJSONString(requestParams);
        String[] paramArray = EncryptUtil.getParam(cipherText);
        System.out.println("params: " + paramArray[0]);
        System.out.println("encSecKey: " + paramArray[1]);
    }
}
