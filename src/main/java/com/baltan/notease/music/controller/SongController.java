package com.baltan.notease.music.controller;

import com.baltan.notease.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Description:
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
     * 根据关键字搜索歌曲
     *
     * @param params
     * @return
     * @throws Exception
     */
    @PostMapping("/searchSongs")
    public Map<String, Object> searchSongs(@RequestBody Map<String, Object> params) throws Exception {
        return songService.searchSongs(params);
    }
}
