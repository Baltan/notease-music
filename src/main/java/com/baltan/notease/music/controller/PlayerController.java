package com.baltan.notease.music.controller;

import com.baltan.notease.music.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Description: 音乐播放控制层
 *
 * @author Baltan
 * @date 2019-12-11 15:04
 */
@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    /**
     * 开始播放歌曲
     *
     * @param params
     * @return
     */
    @PostMapping("/startPlay")
    public Map<String, Object> startPlay(@RequestBody Map<String, Object> params) {
        return playerService.startPlay(params);
    }

    /**
     * 结束播放歌曲
     *
     * @param params
     * @return
     */
    @PostMapping("/stopPlay")
    public Map<String, Object> stopPlay(@RequestBody Map<String, Object> params) {
        return playerService.stopPlay(params);
    }

    /**
     * 开始播放在线歌曲
     *
     * @param params
     * @return
     */
    @PostMapping("/startOnlinePlay")
    public Map<String, Object> startOnlinePlay(@RequestBody Map<String, Object> params) {
        return playerService.startOnlinePlay(params);
    }

    /**
     * 结束播放在线歌曲
     *
     * @param params
     * @return
     */
    @PostMapping("/stopOnlinePlay")
    public Map<String, Object> stopOnlinePlay(@RequestBody Map<String, Object> params) {
        return playerService.stopOnlinePlay(params);
    }

    /**
     * 暂停播放在线歌曲
     *
     * @param params
     * @return
     */
    @PostMapping("/pauseOnlinePlay")
    public Map<String, Object> pauseOnlinePlay(@RequestBody Map<String, Object> params) {
        return playerService.pauseOnlinePlay(params);
    }
}
