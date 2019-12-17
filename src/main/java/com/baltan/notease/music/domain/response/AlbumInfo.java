package com.baltan.notease.music.domain.response;

import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-17 14:21
 */
public class AlbumInfo {
    private Long id;
    private String name;
    private String type;
    private Integer size;
    private String picUrl;
    private Long publishTime;
    private String company;
    private List<String> alias;
    private Artist artist;
    private List<Artist> artists;
    private Integer status;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AlbumInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", picUrl='" + picUrl + '\'' +
                ", publishTime=" + publishTime +
                ", company='" + company + '\'' +
                ", alias=" + alias +
                ", artist=" + artist +
                ", artists=" + artists +
                ", status=" + status +
                '}';
    }
}
