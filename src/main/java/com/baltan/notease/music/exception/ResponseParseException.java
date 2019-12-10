package com.baltan.notease.music.exception;

/**
 * Description: 解析响应报文错误异常
 *
 * @author Baltan
 * @date 2019-12-10 16:26
 */
public class ResponseParseException extends Exception {
    private int code;
    private String message;

    public ResponseParseException(int code, String message) {
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
