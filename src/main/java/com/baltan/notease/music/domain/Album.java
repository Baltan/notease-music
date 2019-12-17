package com.baltan.notease.music.domain;

import java.util.List;

/**
 * Description: 专辑实体类
 *
 * @author Baltan
 * @date 2019-12-10 12:02
 */
public class Album {
    /**
     * 专辑id
     */
    private Long id;
    /**
     * 专辑名称
     */
    private String name;
    /**
     * 专辑封面
     */
    private String albumCoverUrl;
    /**
     * 专辑歌曲数目
     */
    private Integer size;
    /**
     * 专辑类型：EP/Single、专辑
     */
    private String type;
    /**
     * 专辑发行时间
     */
    private Long publishTime;
    /**
     * 专辑发行公司
     */
    private String company;
    /**
     * 专辑别名
     */
    private List<String> alias;
    /**
     * 专辑歌手
     */
    private Artist artist;
    /**
     * 专辑歌手列表
     */
    private List<Artist> artists;
    /**
     * 是否可播放
     */
    private Boolean playable;


    public Album() {
    }

    public Album(Long id, String name, String albumCoverUrl) {
        this.id = id;
        this.name = name;
        this.albumCoverUrl = albumCoverUrl;
    }

    public Album(Long id, String name, String albumCoverUrl, Integer size, String type,
                 Long publishTime, String company, List<String> alias,
                 Artist artist, List<Artist> artists, Boolean playable) {
        this.id = id;
        this.name = name;
        this.albumCoverUrl = albumCoverUrl;
        this.size = size;
        this.type = type;
        this.publishTime = publishTime;
        this.company = company;
        this.alias = alias;
        this.artist = artist;
        this.artists = artists;
        this.playable = playable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbumCoverUrl() {
        return albumCoverUrl;
    }

    public void setAlbumCoverUrl(String albumCoverUrl) {
        this.albumCoverUrl = albumCoverUrl;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public Boolean getPlayable() {
        return playable;
    }

    public void setPlayable(Boolean playable) {
        this.playable = playable;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", albumCoverUrl='" + albumCoverUrl + '\'' +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", publishTime=" + publishTime +
                ", company='" + company + '\'' +
                ", alias=" + alias +
                ", artist=" + artist +
                ", artists=" + artists +
                ", playable=" + playable +
                '}';
    }
}
