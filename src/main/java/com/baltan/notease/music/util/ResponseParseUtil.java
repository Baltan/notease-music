package com.baltan.notease.music.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.baltan.notease.music.constant.CustomizedException;
import com.baltan.notease.music.constant.Response;
import com.baltan.notease.music.domain.Album;
import com.baltan.notease.music.domain.Artist;
import com.baltan.notease.music.domain.Song;
import com.baltan.notease.music.domain.response.SearchSongsResponse;
import com.baltan.notease.music.domain.response.SearchSongsResult;
import com.baltan.notease.music.domain.response.SongInfo;
import com.baltan.notease.music.exception.QueryFailureException;
import com.baltan.notease.music.exception.ResponseParseException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description: 响应报文解析工具类
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

    public static Map<String, Object> searchSongsParse(String json)
            throws ResponseParseException, QueryFailureException {
        Map<String, Object> response = new HashMap<>();

        try {
            SearchSongsResponse searchSongsResponse = JSON.parseObject(json, SearchSongsResponse.class,
                    Feature.IgnoreNotMatch);
            Integer code = searchSongsResponse.getCode();
            SearchSongsResult result = searchSongsResponse.getResult();

            if (code == Response.NETEASE_QUERY_SUCCESSFUL.getCODE()) {
                Integer songCount = result.getSongCount();
                List<SongInfo> songs = result.getSongs();
                List<Song> songList = new LinkedList<>();

                for (SongInfo songInfo : songs) {
                    Long id = songInfo.getId();
                    String songName = songInfo.getName();
                    List<String> alia = songInfo.getAlia();
                    List<Artist> artists =
                            songInfo.getAr().stream()
                                    .map(ar -> new Artist(ar.getId(), ar.getName(), ar.getAlias()))
                                    .collect(Collectors.toList());
                    Album album = new Album(songInfo.getAl().getId(), songInfo.getAl().getName(),
                            songInfo.getAl().getPicUrl());
                    Long duration = null;
                    Integer fee = songInfo.getFee();
                    songList.add(new Song(id, songName, alia, artists, album, duration, fee));
                }
                response.put("songCount", songCount);
                response.put("songList", songList);
            } else {
                throw new QueryFailureException(CustomizedException.QUERY_FAILURE_EXCEPTION.getCODE(),
                        CustomizedException.QUERY_FAILURE_EXCEPTION.getMESSAGE());
            }
        } catch (QueryFailureException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseParseException(CustomizedException.RESPONSE_PARSE_EXCEPTION.getCODE(),
                    CustomizedException.RESPONSE_PARSE_EXCEPTION.getMESSAGE());
        }
        return response;
    }
}
