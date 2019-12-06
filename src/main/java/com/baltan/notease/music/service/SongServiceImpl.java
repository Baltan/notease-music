package com.baltan.notease.music.service;

import com.alibaba.fastjson.JSON;
import com.baltan.notease.music.util.EncryptUtil;
import com.baltan.notease.music.util.HttpUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-06 22:41
 */
@Service
public class SongServiceImpl implements SongService {
    @Override
    public Map<String, Object> searchSongs(String keyWord) throws Exception {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("hlpretag", "<span class=\\\"s-fc7\\\">");
        requestParams.put("hlposttag", "</span>");
        requestParams.put("#/discover", "");
        requestParams.put("s", keyWord);
        requestParams.put("type", "1");
        requestParams.put("offset", "0");
        requestParams.put("total", "true");
        requestParams.put("limit", "30");
        requestParams.put("csrf_token", "");
        String cipherText = JSON.toJSONString(requestParams);
        String[] paramArray = EncryptUtil.getParam(cipherText);

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("params", paramArray[0]);
        paramsMap.put("encSecKey", paramArray[1]);
        String songs = HttpUtil.searchSongs(paramsMap);
        Map<String, Object> response = JSON.parseObject(songs, Map.class);
        return response;
    }
}
