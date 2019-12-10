package com.baltan.notease.music.domain.response;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-10 11:26
 */
public class SearchSongsResponse {
    private Integer code;
    private SearchSongsResult result;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public SearchSongsResult getResult() {
        return result;
    }

    public void setResult(SearchSongsResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SearchSongsResponse{" +
                "code=" + code +
                ", result=" + result +
                '}';
    }
}
