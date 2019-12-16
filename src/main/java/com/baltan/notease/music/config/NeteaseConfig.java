package com.baltan.notease.music.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description: 网易云音乐配置类
 *
 * @author Baltan
 * @date 2019-12-06 15:44
 */
@ConfigurationProperties(prefix = "netease")
@Component
public class NeteaseConfig {
    private String modules;
    private String nonce;
    private String pubKey;
    private String keyAlgorithm;
    private String defaultKeyAlgorithm;
    private String ivParameter;
    private String charset;
    private String searchSongsRequestUrl;
    private String searchLyricRequestUrl;

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    public String getKeyAlgorithm() {
        return keyAlgorithm;
    }

    public void setKeyAlgorithm(String keyAlgorithm) {
        this.keyAlgorithm = keyAlgorithm;
    }

    public String getDefaultKeyAlgorithm() {
        return defaultKeyAlgorithm;
    }

    public void setDefaultKeyAlgorithm(String defaultKeyAlgorithm) {
        this.defaultKeyAlgorithm = defaultKeyAlgorithm;
    }

    public String getIvParameter() {
        return ivParameter;
    }

    public void setIvParameter(String ivParameter) {
        this.ivParameter = ivParameter;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSearchSongsRequestUrl() {
        return searchSongsRequestUrl;
    }

    public void setSearchSongsRequestUrl(String searchSongsRequestUrl) {
        this.searchSongsRequestUrl = searchSongsRequestUrl;
    }

    public String getSearchLyricRequestUrl() {
        return searchLyricRequestUrl;
    }

    public void setSearchLyricRequestUrl(String searchLyricRequestUrl) {
        this.searchLyricRequestUrl = searchLyricRequestUrl;
    }

    @Override
    public String toString() {
        return "NeteaseConfig{" +
                "modules='" + modules + '\'' +
                ", nonce='" + nonce + '\'' +
                ", pubKey='" + pubKey + '\'' +
                ", keyAlgorithm='" + keyAlgorithm + '\'' +
                ", defaultKeyAlgorithm='" + defaultKeyAlgorithm + '\'' +
                ", ivParameter='" + ivParameter + '\'' +
                ", charset='" + charset + '\'' +
                ", searchSongsRequestUrl='" + searchSongsRequestUrl + '\'' +
                ", searchLyricRequestUrl='" + searchLyricRequestUrl + '\'' +
                '}';
    }
}
