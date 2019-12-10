package com.baltan.notease.music.exception;

/**
 * Description: 报文加密异常
 *
 * @author Baltan
 * @date 2019-12-10 17:17
 */
public class EncryptException extends Exception {
    private int code;
    private String message;

    public EncryptException(int code, String message) {
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
