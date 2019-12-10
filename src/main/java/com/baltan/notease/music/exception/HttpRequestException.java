package com.baltan.notease.music.exception;

/**
 * Description: http请求异常
 *
 * @author Baltan
 * @date 2019-12-10 17:29
 */
public class HttpRequestException extends Exception {
    private int code;
    private String message;

    public HttpRequestException(int code, String message) {
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
