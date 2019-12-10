package com.baltan.notease.music.util;

import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.io.IOException;

/**
 * Description: IO流工具类
 *
 * @author Baltan
 * @date 2019-12-10 17:33
 */
@Component
public class IOUtil {
    /**
     * 无法实例化工具类
     */
    private IOUtil() {
    }

    /**
     * 关闭IO流
     *
     * @param io
     * @throws IOException
     */
    public static void close(Closeable io) throws IOException {
        if (io != null) {
            io.close();
        }
    }
}
