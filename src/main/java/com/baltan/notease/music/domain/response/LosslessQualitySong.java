package com.baltan.notease.music.domain.response;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-10 11:39
 */
public class LosslessQualitySong {
    private Integer br;
    private Long size;

    public Integer getBr() {
        return br;
    }

    public void setBr(Integer br) {
        this.br = br;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "LosslessQualitySong{" +
                "br=" + br +
                ", size=" + size +
                '}';
    }
}
