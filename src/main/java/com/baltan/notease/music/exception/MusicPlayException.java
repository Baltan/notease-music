package com.baltan.notease.music.exception;

/**
 * Description: 音乐播放异常
 *
 * @author Baltan
 * @date 2019-12-11 14:17
 */
public class MusicPlayException extends Exception {
    private int code;
    private String message;

    public MusicPlayException(int code, String message) {
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
