package com.baltan.notease.music.domain.response;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-16 16:14
 */
public class SearchLyricResponse {
    private Integer songStatus;
    private Integer lyricVersion;
    private String lyric;
    private Integer code;

    public Integer getSongStatus() {
        return songStatus;
    }

    public void setSongStatus(Integer songStatus) {
        this.songStatus = songStatus;
    }

    public Integer getLyricVersion() {
        return lyricVersion;
    }

    public void setLyricVersion(Integer lyricVersion) {
        this.lyricVersion = lyricVersion;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SearchLyricResponse{" +
                "songStatus=" + songStatus +
                ", lyricVersion=" + lyricVersion +
                ", lyric='" + lyric + '\'' +
                ", code=" + code +
                '}';
    }
}
