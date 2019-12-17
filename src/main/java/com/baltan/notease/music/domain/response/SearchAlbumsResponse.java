package com.baltan.notease.music.domain.response;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-17 14:19
 */
public class SearchAlbumsResponse {
    private Integer code;
    private SearchAlbumsResult result;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public SearchAlbumsResult getResult() {
        return result;
    }

    public void setResult(SearchAlbumsResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SearchAlbumsResponse{" +
                "code=" + code +
                ", result=" + result +
                '}';
    }
}
