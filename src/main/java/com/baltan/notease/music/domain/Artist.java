package com.baltan.notease.music.domain;

import java.util.List;

/**
 * Description: 歌手实体类
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
    /**
     * 歌手别名
     */
    private List<String> alias;

    public Artist() {
    }

    public Artist(Long id, String name, List<String> alias) {
        this.id = id;
        this.name = name;
        this.alias = alias;
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

    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alias=" + alias +
                '}';
    }
}
