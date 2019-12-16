package com.baltan.notease.music.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.baltan.notease.music.constant.CustomizedException;
import com.baltan.notease.music.constant.Response;
import com.baltan.notease.music.domain.Album;
import com.baltan.notease.music.domain.Artist;
import com.baltan.notease.music.domain.Song;
import com.baltan.notease.music.domain.response.*;
import com.baltan.notease.music.exception.QueryFailureException;
import com.baltan.notease.music.exception.ResponseParseException;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * 查询歌曲返回报文解析
     *
     * @param json
     * @return
     * @throws ResponseParseException
     * @throws QueryFailureException
     */
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
                List<Song> playableSongList = new LinkedList<>();
                List<Song> notPlayableSongList = new LinkedList<>();

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
                    String duration = DataUtil.second2MinuteAndSecond((int) (songInfo.getDt() / 1000));
                    Integer fee = songInfo.getFee();
                    Privilege privilege = songInfo.getPrivilege();
                    Boolean playable = isPlayable(privilege);

                    if (playable) {
                        playableSongList
                                .add(new Song(id, songName, alia, artists, album, duration, fee, true));
                    } else {
                        notPlayableSongList
                                .add(new Song(id, songName, alia, artists, album, duration, fee, false));
                    }
                }
                response.put("songCount", songCount);
                response.put("playableSongList", playableSongList);
                response.put("notPlayableSongList", notPlayableSongList);
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

    /**
     * 查询歌词返回报文解析
     *
     * @param json
     * @return
     * @throws QueryFailureException
     * @throws ResponseParseException
     */
    public static Map<String, Object> searchLyricParse(String json)
            throws QueryFailureException, ResponseParseException {
        Map<String, Object> response = new HashMap<>();

        try {
            SearchLyricResponse searchSongsResponse = JSON.parseObject(json, SearchLyricResponse.class,
                    Feature.IgnoreNotMatch);
            Integer code = searchSongsResponse.getCode();
            String lyric = searchSongsResponse.getLyric();

            if (code == Response.NETEASE_QUERY_SUCCESSFUL.getCODE()) {
                if (StringUtils.isNotEmpty(lyric)) {
                    response.put("lyric", lyric);
                } else {
                    response.put("lyric", lyric);
                }
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

    /**
     * 判断歌曲能否播放
     * 参考：<a href="https://blog.csdn.net/qq_36779888/article/details/90738012"></a>
     *
     * @param privilege
     * @return
     */
    private static boolean isPlayable(Privilege privilege) {
        int fee = privilege.getFee();
        int payed = privilege.getPayed();
        int pl = privilege.getPl();
        int dl = privilege.getDl();

        if ((fee == 0 || payed != 0) && pl > 0 && dl == 0) {
            return false;
        }

        if (pl == 0 && dl == 0) {
            return false;
        }
        return true;
    }
}
