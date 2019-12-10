package com.baltan.notease.music.domain.response;

import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-10 11:30
 */
public class SongInfo {
    private Long id;
    private String name;
    private List<Artist> ar;
    private Album al;
    private MediumQualitySong m;
    private HighQualitySong h;
    private LosslessQualitySong l;

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

    public List<Artist> getAr() {
        return ar;
    }

    public void setAr(List<Artist> ar) {
        this.ar = ar;
    }

    public Album getAl() {
        return al;
    }

    public void setAl(Album al) {
        this.al = al;
    }

    public MediumQualitySong getM() {
        return m;
    }

    public void setM(MediumQualitySong m) {
        this.m = m;
    }

    public HighQualitySong getH() {
        return h;
    }

    public void setH(HighQualitySong h) {
        this.h = h;
    }

    public LosslessQualitySong getL() {
        return l;
    }

    public void setL(LosslessQualitySong l) {
        this.l = l;
    }

    @Override
    public String toString() {
        return "SongInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ar=" + ar +
                ", al=" + al +
                ", m=" + m +
                ", h=" + h +
                ", l=" + l +
                '}';
    }
}
