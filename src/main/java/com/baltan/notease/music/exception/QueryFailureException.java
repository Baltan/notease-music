package com.baltan.notease.music.exception;

/**
 * Description: 查询失败异常
 *
 * @author Baltan
 * @date 2019-12-10 16:40
 */
public class QueryFailureException extends Exception {
    private int code;
    private String message;

    public QueryFailureException(int code, String message) {
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
