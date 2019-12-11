package com.baltan.notease.music.constant;

/**
 * Description: 自定义异常枚举类
 *
 * @author Baltan
 * @date 2019-12-10 16:28
 */
public enum CustomizedException {
    UNKNOWN_EXCEPTION(999999, "未知错误"),
    RESPONSE_PARSE_EXCEPTION(100000, "响应报文解析错误"),
    QUERY_FAILURE_EXCEPTION(100001, "查询失败"),
    DOWNLOAD_FAILURE_EXCEPTION(100002, "下载失败"),
    ENCRYPT_EXCEPTION(100003, "报文加密错误"),
    HTTP_REQUEST_EXCEPTION(100004, "http请求错误"),
    DATA_FORMAT_EXCEPTION(100005, "数据格式转换错误"),
    MUSIC_PLAY_EXCEPTION(100006, "音乐播放失败");

    private final int CODE;
    private final String MESSAGE;

    CustomizedException(int CODE, String MESSAGE) {
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
