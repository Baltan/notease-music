package com.baltan.notease.music.exception;

/**
 * Description: 下载失败异常
 *
 * @author Baltan
 * @date 2019-12-10 17:09
 */
public class DownloadFailureException extends Exception {
    private int code;
    private String message;

    public DownloadFailureException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
