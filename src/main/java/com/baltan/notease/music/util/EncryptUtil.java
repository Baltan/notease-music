package com.baltan.notease.music.util;

import com.baltan.notease.music.config.NeteaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.util.Random;

/**
 * Description: 参考：<a href="https://zhuanlan.zhihu.com/p/36561112"></a>
 *
 * @author Baltan
 * @date 2019-12-04 21:58
 */
@Component
public class EncryptUtil {
    private static NeteaseConfig neteaseConfig;

    /**
     * 无法实例化工具类
     */
    private EncryptUtil() {
    }

    /**
     * 产生一个长度为keyLength的随机字符串
     *
     * @param keyLength
     * @return
     */
    @Deprecated
    private static String getSecretKey(int keyLength) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder builder = new StringBuilder(keyLength);
        Random rand = new Random();
        int charsLength = str.length();

        for (int i = 0; i < keyLength; i++) {
            builder.append(str.charAt(rand.nextInt(charsLength)));
        }
        return builder.toString();
    }

    /**
     * AES加密
     *
     * @param clearText
     * @param secretKey
     * @return
     * @throws Exception
     */
    private static String aesEncrypt(String clearText, String secretKey) throws Exception {
        /**
         * 参考：<a href="https://www.cnblogs.com/lingyejun/p/10971308.html"></a>
         */
        Cipher cipher = Cipher.getInstance(neteaseConfig.getDefaultKeyAlgorithm());
        byte[] raw = secretKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, neteaseConfig.getKeyAlgorithm());
        IvParameterSpec iv = new IvParameterSpec(neteaseConfig.getIvParameter().getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(clearText.getBytes(neteaseConfig.getCharset()));
        return new BASE64Encoder().encode(encrypted);
    }

    /**
     * RSA加密
     *
     * @param secretKey
     * @return
     */
    private static String rsaEncrypt(String secretKey) {
        secretKey = new StringBuilder(secretKey).reverse().toString();
        String hexString = string2HexString(secretKey);
        BigInteger hexStringBigInteger = string2DecBigInteger(hexString);
        int pubKeyBigInteger = string2DecInteger(neteaseConfig.getPubKey());
        BigInteger modulusBigInteger = string2DecBigInteger(neteaseConfig.getModules());
        BigInteger decBigInteger = hexStringBigInteger.pow(pubKeyBigInteger).mod(modulusBigInteger);
        String anotherHexString = decBigInteger2HexString(decBigInteger);
        String result = appendAtTheEndAtTheHead(anotherHexString, 256 - anotherHexString.length(), '0');
        return result;
    }

    /**
     * 字符串转为十六进制字符串
     * 参考：<a href="https://www.cnblogs.com/carryLess/p/6889378.html"></a>
     *
     * @param s
     * @return
     */
    private static String string2HexString(String s) {
        StringBuilder builder = new StringBuilder();

        for (char c : s.toCharArray()) {
            builder.append(Integer.toHexString((int) c));
        }
        return builder.toString();
    }

    /**
     * 十进制大数转为十六进制字符串
     *
     * @param decBigInteger
     * @return
     */
    private static String decBigInteger2HexString(BigInteger decBigInteger) {
        return decBigInteger.toString(16);
    }

    /**
     * 十六进制字符串转为十进制整型
     *
     * @param hexString
     * @return
     */
    private static int string2DecInteger(String hexString) {
        return Integer.valueOf(hexString, 16);
    }

    /**
     * 十六进制字符串转为十进制大数
     *
     * @param hexString
     * @return
     */
    private static BigInteger string2DecBigInteger(String hexString) {
        return new BigInteger(hexString, 16);
    }

    /**
     * 在字符串头部补足一定长度的字符
     *
     * @param oldString
     * @param filledLength
     * @param filledCharacter
     * @return
     */
    private static String appendAtTheEndAtTheHead(String oldString, int filledLength, char filledCharacter) {
        StringBuilder builder = new StringBuilder(oldString);

        for (int i = 0; i < filledLength; i++) {
            builder.insert(0, filledCharacter);
        }
        return builder.toString();
    }

    public static String[] getParam(String cipherText) throws Exception {
//        /**
//         * 产生一个长度为16的随机字符串
//         */
//        String secretKey = getSecretKey(16);
        String secretKey = neteaseConfig.getNonce();
        String params = aesEncrypt(aesEncrypt(cipherText, secretKey), neteaseConfig.getNonce());
        String encSecKey = rsaEncrypt(secretKey);
        return new String[]{params, encSecKey};
    }

    @Autowired
    public void setNeteaseConfig(NeteaseConfig neteaseConfig) {
        EncryptUtil.neteaseConfig = neteaseConfig;
    }
}
