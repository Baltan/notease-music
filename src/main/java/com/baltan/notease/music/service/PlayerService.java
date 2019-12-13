package com.baltan.notease.music.service;

import java.util.Map;

/**
 * Description: 音乐播放接口
 *
 * @author Baltan
 * @date 2019-12-11 15:00
 */
public interface PlayerService {
    /**
     * 开始播放本地歌曲
     *
     * @param params
     * @return
     */
    Map<String, Object> startLocalPlay(Map<String, Object> params);

    /**
     * 结束播放本地歌曲
     *
     * @param params
     * @return
     */
    Map<String, Object> stopLocalPlay(Map<String, Object> params);

    /**
     * 开始播放在线歌曲
     *
     * @param params
     * @return
     */
    Map<String, Object> startOnlinePlay(Map<String, Object> params);

    /**
     * 结束播放在线歌曲
     *
     * @param params
     * @return
     */
    Map<String, Object> stopOnlinePlay(Map<String, Object> params);
}
