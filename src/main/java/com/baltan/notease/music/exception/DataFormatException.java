package com.baltan.notease.music.exception;

/**
 * Description: 数据转换异常
 *
 * @author Baltan
 * @date 2019-12-10 19:59
 */
public class DataFormatException extends Exception {
    private int code;
    private String message;

    public DataFormatException(int code, String message) {
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
