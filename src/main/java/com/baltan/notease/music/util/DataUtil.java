package com.baltan.notease.music.util;

import com.alibaba.fastjson.JSON;
import com.baltan.notease.music.constant.CustomizedException;
import com.baltan.notease.music.exception.DataFormatException;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Map;

/**
 * Description: 数据格式转换工具类
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

    /**
     * JSON字符串转Map
     *
     * @param content
     * @return
     * @throws DataFormatException
     */
    public static Map<String, Object> string2Map(String content) throws DataFormatException {
        try {
            return JSON.parseObject(content, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataFormatException(CustomizedException.DATA_FORMAT_EXCEPTION.getCODE(),
                    CustomizedException.DATA_FORMAT_EXCEPTION.getMESSAGE());
        }
    }

    /**
     * 字符串转为十六进制字符串
     * 参考：<a href="https://www.cnblogs.com/carryLess/p/6889378.html"></a>
     *
     * @param s
     * @return
     * @throws DataFormatException
     */
    public static String string2HexString(String s) throws DataFormatException {
        try {
            StringBuilder builder = new StringBuilder();

            for (char c : s.toCharArray()) {
                builder.append(Integer.toHexString((int) c));
            }
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataFormatException(CustomizedException.DATA_FORMAT_EXCEPTION.getCODE(),
                    CustomizedException.DATA_FORMAT_EXCEPTION.getMESSAGE());
        }
    }

    /**
     * 十进制大数转为十六进制字符串
     *
     * @param decBigInteger
     * @return
     * @throws DataFormatException
     */
    public static String decBigInteger2HexString(BigInteger decBigInteger) throws DataFormatException {
        try {
            return decBigInteger.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataFormatException(CustomizedException.DATA_FORMAT_EXCEPTION.getCODE(),
                    CustomizedException.DATA_FORMAT_EXCEPTION.getMESSAGE());
        }
    }

    /**
     * 十六进制字符串转为十进制整型
     *
     * @param hexString
     * @return
     * @throws DataFormatException
     */
    public static int string2DecInteger(String hexString) throws DataFormatException {
        try {
            return Integer.valueOf(hexString, 16);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new DataFormatException(CustomizedException.DATA_FORMAT_EXCEPTION.getCODE(),
                    CustomizedException.DATA_FORMAT_EXCEPTION.getMESSAGE());
        }
    }

    /**
     * 十六进制字符串转为十进制大数
     *
     * @param hexString
     * @return
     * @throws DataFormatException
     */
    public static BigInteger string2DecBigInteger(String hexString) throws DataFormatException {
        try {
            return new BigInteger(hexString, 16);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataFormatException(CustomizedException.DATA_FORMAT_EXCEPTION.getCODE(),
                    CustomizedException.DATA_FORMAT_EXCEPTION.getMESSAGE());
        }
    }
}
