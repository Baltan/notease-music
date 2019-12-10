package com.baltan.notease.music.domain;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-10 12:00
 */
public class Artist {
    /**
     * 歌手id
     */
    private Long id;
    /**
     * 歌手姓名
     */
    private String name;

    public Artist() {
    }

    public Artist(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
