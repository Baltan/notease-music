package com.baltan.notease.music.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description: 文件下载配置类
 *
 * @author Baltan
 * @date 2019-12-10 15:48
 */
@ConfigurationProperties(prefix = "download")
@Component
public class DownloadConfig {
    private String directoryPath;
    private String songUrl;
    private String songSuffix;
    private String charset;

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getSongSuffix() {
        return songSuffix;
    }

    public void setSongSuffix(String songSuffix) {
        this.songSuffix = songSuffix;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
