package com.baltan.notease.music.domain;

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
    private Integer id;
    /**
     * 歌名
     */
    private String songName;
    /**
     * 歌手
     */
    private String artist;
    /**
     * 专辑
     */
    private String album;
    /**
     * 时长
     */
    private int duration;
    /**
     * 专辑封面
     */
    private String albumCoverUrl;

    public Song() {
    }

    public Song(Integer id, String songName, String artist, String album, int duration,
                String albumCoverUrl) {
        this.id = id;
        this.songName = songName;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.albumCoverUrl = albumCoverUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAlbumCoverUrl() {
        return albumCoverUrl;
    }

    public void setAlbumCoverUrl(String albumCoverUrl) {
        this.albumCoverUrl = albumCoverUrl;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", songName='" + songName + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", duration=" + duration +
                ", albumCoverUrl='" + albumCoverUrl + '\'' +
                '}';
    }
}
