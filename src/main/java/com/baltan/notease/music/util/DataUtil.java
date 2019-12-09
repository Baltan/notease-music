package com.baltan.notease.music.util;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-09 20:45
 */
@Component
public class DataUtil {
    /**
     * 无法实例化工具类
     */
    private DataUtil() {
    }

    public static Map<String, Object> string2Map(String content) {
        return JSON.parseObject(content, Map.class);
    }
}
