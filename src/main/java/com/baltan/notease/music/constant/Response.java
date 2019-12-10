package com.baltan.notease.music.constant;

/**
 * Description: 响应成功枚举类
 *
 * @author Baltan
 * @date 2019-12-10 10:13
 */
public enum Response {
    SUCCESSFUL(666666, "操作成功"),
    NETEASE_QUERY_SUCCESSFUL(200, "网易云音乐查询成功");

    private final int CODE;
    private final String MESSAGE;

    Response(int CODE, String MESSAGE) {
        this.CODE = CODE;
        this.MESSAGE = MESSAGE;
    }

    public int getCODE() {
        return CODE;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }
}
