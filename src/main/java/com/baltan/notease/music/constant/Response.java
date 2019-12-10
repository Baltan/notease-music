package com.baltan.notease.music.constant;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-10 10:13
 */
public enum Response {
    SUCCESSFUL(200, "查询成功"), FAILURE(999999, "查询失败");

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
