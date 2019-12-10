package com.baltan.notease.music.domain.response;

import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-10 11:28
 */
public class SearchSongsResult {
    private Integer songCount;
    private List<SongInfo> songs;

    public Integer getSongCount() {
        return songCount;
    }

    public void setSongCount(Integer songCount) {
        this.songCount = songCount;
    }

    public List<SongInfo> getSongs() {
        return songs;
    }

    public void setSongs(List<SongInfo> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "SearchSongsResult{" +
                "songCount=" + songCount +
                ", songs=" + songs +
                '}';
    }
}
