package com.baltan.notease.music.domain.response;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-10 11:35
 */
public class Album {
    private Long id;
    private String name;
    private String picUrl;

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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }
}
