package com.baltan.notease.music.service;

import com.alibaba.fastjson.JSON;
import com.baltan.notease.music.config.NeteaseConfig;
import com.baltan.notease.music.constant.CustomizedException;
import com.baltan.notease.music.constant.Response;
import com.baltan.notease.music.constant.SearchType;
import com.baltan.notease.music.exception.DownloadFailureException;
import com.baltan.notease.music.exception.EncryptException;
import com.baltan.notease.music.exception.QueryFailureException;
import com.baltan.notease.music.exception.ResponseParseException;
import com.baltan.notease.music.util.EncryptUtil;
import com.baltan.notease.music.util.FileUtil;
import com.baltan.notease.music.util.HttpUtil;
import com.baltan.notease.music.util.ResponseParseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 歌曲操作实现层
 *
 * @author Baltan
 * @date 2019-12-06 22:41
 */
@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private NeteaseConfig neteaseConfig;

    /**
     * 搜索歌曲
     *
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> searchSongs(Map<String, Object> params) {
        Map<String, Object> response = new HashMap<>();
        int responseCode = Response.SUCCESSFUL.getCODE();
        String responseMessage = Response.SUCCESSFUL.getMESSAGE();

        try {
            String keyWord = (String) params.get("keyWord");
            int pageNumber = (int) params.get("pageNumber");
            final int COUNT_PER_PAGE = 20;
            String offset = String.valueOf((pageNumber - 1) * COUNT_PER_PAGE);

            Map<String, String> requestParams = new HashMap<>(9);
            requestParams.put("hlpretag", "<span class=\\\"s-fc7\\\">");
            requestParams.put("hlposttag", "</span>");
            requestParams.put("#/discover", "");
            requestParams.put("s", keyWord);
            /**
             * 1：单曲；10：专辑；100：歌手；1000：歌单；1002：用户
             */
            requestParams.put("type", SearchType.SONG.getVALUE());
            /**
             * 页码偏移量
             */
            requestParams.put("offset", offset);
            requestParams.put("total", "true");
            /**
             * 返回数量
             */
            requestParams.put("limit", String.valueOf(COUNT_PER_PAGE));
            requestParams.put("csrf_token", "");
            String cipherText = JSON.toJSONString(requestParams);
            String[] paramArray = EncryptUtil.getParam(cipherText);

            Map<String, String> paramsMap = new HashMap<>(2);
            paramsMap.put("params", paramArray[0]);
            paramsMap.put("encSecKey", paramArray[1]);
            String json = HttpUtil.post(paramsMap, neteaseConfig.getSearchSongsRequestUrl());
            response = ResponseParseUtil.searchSongsParse(json);
        } catch (QueryFailureException e) {
            responseCode = e.getCode();
            responseMessage = e.getMessage();
            e.printStackTrace();
        } catch (ResponseParseException e) {
            responseCode = e.getCode();
            responseMessage = e.getMessage();
            e.printStackTrace();
        } catch (EncryptException e) {
            responseCode = e.getCode();
            responseMessage = e.getMessage();
            e.printStackTrace();
        } catch (Exception e) {
            responseCode = CustomizedException.UNKNOWN_EXCEPTION.getCODE();
            responseMessage = CustomizedException.UNKNOWN_EXCEPTION.getMESSAGE();
            e.printStackTrace();
        } finally {
            response.put("responseCode", responseCode);
            response.put("responseMessage", responseMessage);
        }
        return response;
    }

    /**
     * 下载歌曲
     *
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> downloadSong(Map<String, Object> params) {
        Map<String, Object> response = new HashMap<>();
        int responseCode = Response.SUCCESSFUL.getCODE();
        String responseMessage = Response.SUCCESSFUL.getMESSAGE();

        try {
            int id = (int) params.get("id");
            List<String> artistNames = (List<String>) params.get("artistNames");
            String songName = (String) params.get("songName");
            FileUtil.downloadSong(id, artistNames, songName);
        } catch (DownloadFailureException e) {
            responseCode = e.getCode();
            responseMessage = e.getMessage();
            e.printStackTrace();
        } catch (Exception e) {
            responseCode = CustomizedException.UNKNOWN_EXCEPTION.getCODE();
            responseMessage = CustomizedException.UNKNOWN_EXCEPTION.getMESSAGE();
            e.printStackTrace();
        } finally {
            response.put("responseCode", responseCode);
            response.put("responseMessage", responseMessage);
        }
        return response;
    }
}
