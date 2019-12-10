package com.baltan.notease.music.domain;

import java.util.List;

/**
 * Description: MP3信息实体类
 *
 * @author Baltan
 * @date 2019-11-27 22:51
 */
public class Song {
    /**
     * 歌曲id
     */
    private Long id;
    /**
     * 歌名
     */
    private String songName;
    /**
     * 歌手
     */
    private List<Artist> artist;
    /**
     * 专辑
     */
    private Album album;
    /**
     * 时长
     */
    private Long duration;

    public Song() {
    }

    public Song(Long id, String songName, List<Artist> artist, Album album, Long duration) {
        this.id = id;
        this.songName = songName;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public List<Artist> getArtist() {
        return artist;
    }

    public void setArtist(List<Artist> artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", songName='" + songName + '\'' +
                ", artist=" + artist +
                ", album=" + album +
                ", duration=" + duration +
                '}';
    }
}
