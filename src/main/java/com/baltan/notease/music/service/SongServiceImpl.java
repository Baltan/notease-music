package com.baltan.notease.music.service;

import com.alibaba.fastjson.JSON;
import com.baltan.notease.music.config.NeteaseConfig;
import com.baltan.notease.music.constant.SearchType;
import com.baltan.notease.music.util.EncryptUtil;
import com.baltan.notease.music.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private NeteaseConfig neteaseConfig;

    @Override
    public Map<String, Object> searchSongs(String keyWord) throws Exception {
        Map<String, String> requestParams = new HashMap<>(9);
        requestParams.put("hlpretag", "<span class=\\\"s-fc7\\\">");
        requestParams.put("hlposttag", "</span>");
        requestParams.put("#/discover", "");
        requestParams.put("s", keyWord);
        /**
         * 1：单曲；10：专辑；100：歌手；1000：歌单；1002：用户
         */
        requestParams.put("type", SearchType.SONG.name());
        /**
         * 页码偏移量
         */
        requestParams.put("offset", "0");
        requestParams.put("total", "true");
        /**
         * 返回数量
         */
        requestParams.put("limit", "70");
        requestParams.put("csrf_token", "");
        String cipherText = JSON.toJSONString(requestParams);
        String[] paramArray = EncryptUtil.getParam(cipherText);

        Map<String, String> paramsMap = new HashMap<>(2);
        paramsMap.put("params", paramArray[0]);
        paramsMap.put("encSecKey", paramArray[1]);
        Map<String, Object> response = HttpUtil.post(paramsMap, neteaseConfig.getSearchSongsRequestUrl());
        return response;
    }
}
