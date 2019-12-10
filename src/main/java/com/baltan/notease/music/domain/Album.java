package com.baltan.notease.music.domain;

/**
 * Description:
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

    public Album() {
    }

    public Album(Long id, String name, String albumCoverUrl) {
        this.id = id;
        this.name = name;
        this.albumCoverUrl = albumCoverUrl;
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

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", albumCoverUrl='" + albumCoverUrl + '\'' +
                '}';
    }
}
