package com.baltan.notease.music.domain.response;

import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-10 11:33
 */
public class Artist {
    private Long id;
    private String name;
    private List<String> alias;

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
