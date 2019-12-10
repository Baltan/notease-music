package com.baltan.notease.music.service;

import java.util.Map;

/**
 * Description: 歌曲操作接口
 *
 * @author Baltan
 * @date 2019-12-06 22:40
 */
public interface SongService {
    /**
     * 搜索歌曲
     *
     * @param params
     * @return
     */
    Map<String, Object> searchSongs(Map<String, Object> params);

    /**
     * 下载歌曲
     *
     * @param params
     * @return
     */
    Map<String, Object> downloadSong(Map<String, Object> params);
}
