package com.baltan.notease.music.controller;

import com.baltan.notease.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @param keyWord
     * @return
     * @throws Exception
     */
    @GetMapping("/get/{keyWord}")
    public Map<String, Object> searchSongs(@PathVariable String keyWord) throws Exception {
        return songService.searchSongs(keyWord);
    }
}
