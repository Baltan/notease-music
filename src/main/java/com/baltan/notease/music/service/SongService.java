package com.baltan.notease.music.service;

import java.util.Map;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-06 22:40
 */
public interface SongService {
    /**
     * 根据关键字搜索歌曲
     *
     * @param params
     * @return
     * @throws Exception
     */
    Map<String, Object> searchSongs(Map<String, Object> params) throws Exception;
}
