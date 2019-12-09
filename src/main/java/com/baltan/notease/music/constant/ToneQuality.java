package com.baltan.notease.music.constant;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-09 22:02
 */
public enum ToneQuality {
    /**
     * 一般音质
     */
    NORMAL(64000),
    /**
     * 较高音质
     */
    MEDIUM(128000),
    /**
     * 极高音质
     */
    HIGH(192000),
    /**
     * 无损音质
     */
    LOSSLESS(320000);
    /**
     * 比特率
     */
    private final int BIT_RATE;

    ToneQuality(int BIT_RATE) {
        this.BIT_RATE = BIT_RATE;
    }

    public int getBIT_RATE() {
        return BIT_RATE;
    }
}
