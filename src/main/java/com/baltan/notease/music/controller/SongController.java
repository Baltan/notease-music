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

    /**
     * 搜索歌词
     *
     * @param params
     * @return
     */
    @PostMapping("/searchLyric")
    public Map<String, Object> searchLyric(@RequestBody Map<String, Object> params) {
        return songService.searchLyric(params);
    }

    /**
     * 下载歌词
     *
     * @param params
     * @return
     */
    @PostMapping("/downloadLyric")
    public Map<String, Object> downloadLyric(@RequestBody Map<String, Object> params) {
        return songService.downloadLyric(params);
    }

    /**
     * 搜索专辑
     *
     * @param params
     * @return
     */
    @PostMapping("/searchAlbum")
    public Map<String, Object> searchAlbum(@RequestBody Map<String, Object> params) {
        return songService.searchAlbum(params);
    }
}
