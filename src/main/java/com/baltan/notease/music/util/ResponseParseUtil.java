package com.baltan.notease.music.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.baltan.notease.music.constant.Response;
import com.baltan.notease.music.domain.Album;
import com.baltan.notease.music.domain.Artist;
import com.baltan.notease.music.domain.Song;
import com.baltan.notease.music.domain.response.SearchSongsResponse;
import com.baltan.notease.music.domain.response.SearchSongsResult;
import com.baltan.notease.music.domain.response.SongInfo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-10 10:03
 */
@Component
public class ResponseParseUtil {
    /**
     * 无法实例化工具类
     */
    private ResponseParseUtil() {
    }

    public static Map<String, Object> searchSongsParse(String json) {
        Map<String, Object> response = new HashMap<>();
        int responseCode = -1;
        String responseMessage = "";

        try {
            SearchSongsResponse searchSongsResponse = JSON.parseObject(json, SearchSongsResponse.class,
                    Feature.IgnoreNotMatch);
            Integer code = searchSongsResponse.getCode();
            SearchSongsResult result = searchSongsResponse.getResult();

            if (code == Response.SUCCESSFUL.getCODE()) {
                responseCode = Response.SUCCESSFUL.getCODE();
                responseMessage = Response.SUCCESSFUL.getMESSAGE();
                Integer songCount = result.getSongCount();
                List<SongInfo> songs = result.getSongs();
                List<Song> songList = new LinkedList<>();

                for (SongInfo songInfo : songs) {
                    Long id = songInfo.getId();
                    String songName = songInfo.getName();
                    List<Artist> artists =
                            songInfo.getAr().stream().map(ar -> new Artist(ar.getId(), ar.getName()))
                                    .collect(Collectors.toList());
                    Album album = new Album(songInfo.getAl().getId(), songInfo.getAl().getName(),
                            songInfo.getAl().getPicUrl());
                    Long duration = null;
                    songList.add(new Song(id, songName, artists, album, duration));
                }
                response.put("songCount", songCount);
                response.put("songList", songList);
            } else {
                responseCode = Response.FAILURE.getCODE();
                responseMessage = Response.FAILURE.getMESSAGE();
            }
        } catch (Exception e) {
            responseCode = Response.FAILURE.getCODE();
            responseMessage = Response.FAILURE.getMESSAGE();
            e.printStackTrace();
        } finally {
            response.put("responseCode", responseCode);
            response.put("responseMessage", responseMessage);
        }
        return response;
    }
}
