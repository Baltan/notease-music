package com.baltan.notease.music.domain;

import java.util.List;

/**
 * Description: 歌曲实体类
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
     * 歌曲别名
     */
    private List<String> alia;
    /**
     * 歌手
     */
    private List<Artist> artists;
    /**
     * 专辑
     */
    private Album album;
    /**
     * 时长
     */
    private Long duration;
    /**
     * 费用
     */
    private Integer fee;
    /**
     * 是否可播放
     */
    private Boolean playable;

    public Song() {
    }

    public Song(Long id, String songName, List<String> alia,
                List<Artist> artists, Album album, Long duration, Integer fee, Boolean playable) {
        this.id = id;
        this.songName = songName;
        this.alia = alia;
        this.artists = artists;
        this.album = album;
        this.duration = duration;
        this.fee = fee;
        this.playable = playable;
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

    public List<String> getAlia() {
        return alia;
    }

    public void setAlia(List<String> alia) {
        this.alia = alia;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
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

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Boolean getPlayable() {
        return playable;
    }

    public void setPlayable(Boolean playable) {
        this.playable = playable;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", songName='" + songName + '\'' +
                ", alia=" + alia +
                ", artists=" + artists +
                ", album=" + album +
                ", duration=" + duration +
                ", fee=" + fee +
                ", playable=" + playable +
                '}';
    }
}
