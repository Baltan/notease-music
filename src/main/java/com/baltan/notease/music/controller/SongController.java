package com.baltan.notease.music.controller;

import com.baltan.notease.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Description: 歌曲操作控制层
 *
 * @author Baltan
 * @date 2019-12-06 22:34
 */
@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    private SongService songService;

    /**
     * 搜索歌曲
     *
     * @param params
     * @return
     */
    @PostMapping("/searchSongs")
    public Map<String, Object> searchSongs(@RequestBody Map<String, Object> params) {
        return songService.searchSongs(params);
    }

    /**
     * 下载歌曲
     *
     * @param params
     * @return
     */
    @PostMapping("/downloadSong")
    public Map<String, Object> downloadSong(@RequestBody Map<String, Object> params) {
        return songService.downloadSong(params);
    }
}
