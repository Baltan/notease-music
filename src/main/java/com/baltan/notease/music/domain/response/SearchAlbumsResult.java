package com.baltan.notease.music.domain.response;

import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-17 14:20
 */
public class SearchAlbumsResult {
    private Integer albumCount;
    private List<AlbumInfo> albums;

    public Integer getAlbumCount() {
        return albumCount;
    }

    public void setAlbumCount(Integer albumCount) {
        this.albumCount = albumCount;
    }

    public List<AlbumInfo> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumInfo> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "SearchAlbumsResult{" +
                "albumCount=" + albumCount +
                ", albums=" + albums +
                '}';
    }
}
