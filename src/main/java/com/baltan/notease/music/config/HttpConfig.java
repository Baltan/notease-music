package com.baltan.notease.music.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description: http请求配置类
 *
 * @author Baltan
 * @date 2019-12-06 17:02
 */
@ConfigurationProperties(prefix = "http")
@Component
public class HttpConfig {
    private String contentTypeForm;
    private String contentTypeJson;
    private String contentTypeFormData;
    private String charset;

    public String getContentTypeForm() {
        return contentTypeForm;
    }

    public void setContentTypeForm(String contentTypeForm) {
        this.contentTypeForm = contentTypeForm;
    }

    public String getContentTypeJson() {
        return contentTypeJson;
    }

    public void setContentTypeJson(String contentTypeJson) {
        this.contentTypeJson = contentTypeJson;
    }

    public String getContentTypeFormData() {
        return contentTypeFormData;
    }

    public void setContentTypeFormData(String contentTypeFormData) {
        this.contentTypeFormData = contentTypeFormData;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    @Override
    public String toString() {
        return "HttpConfig{" +
                "contentTypeForm='" + contentTypeForm + '\'' +
                ", contentTypeJson='" + contentTypeJson + '\'' +
                ", contentTypeFormData='" + contentTypeFormData + '\'' +
                ", charset='" + charset + '\'' +
                '}';
    }
}
