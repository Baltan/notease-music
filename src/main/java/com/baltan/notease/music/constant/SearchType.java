package com.baltan.notease.music.constant;

/**
 * Description: 查询类型枚举类
 *
 * @author Baltan
 * @date 2019-12-09 21:49
 */
public enum SearchType {
    /**
     * 单曲
     */
    SONG("1"),
    /**
     * 专辑
     */
    ALBUM("10"),
    /**
     * 歌手
     */
    SINGER("100"),
    /**
     * 歌单
     */
    SONG_LIST("1000"),
    /**
     * 用户
     */
    USER("1002"),
    /**
     * MV
     */
    MV("1004"),
    /**
     * 歌词
     */
    LYRIC("1006"),
    /**
     * 主播电台
     */
    RADIO("1009");

    private final String VALUE;

    SearchType(String VALUE) {
        this.VALUE = VALUE;
    }

    public String getVALUE() {
        return VALUE;
    }
}