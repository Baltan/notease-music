package com.baltan.notease.music.util;

import com.baltan.notease.music.config.NeteaseConfig;
import com.baltan.notease.music.constant.CustomizedException;
import com.baltan.notease.music.exception.EncryptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Description: 报文加密工具类
 * 参考：<a href="https://zhuanlan.zhihu.com/p/36561112"></a>
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
     * @throws EncryptException
     */
    @Deprecated
    private static String getSecretKey(int keyLength) throws EncryptException {
        try {
            String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder builder = new StringBuilder(keyLength);
            Random rand = new Random();
            int charsLength = str.length();

            for (int i = 0; i < keyLength; i++) {
                builder.append(str.charAt(rand.nextInt(charsLength)));
            }
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new EncryptException(CustomizedException.ENCRYPT_EXCEPTION.getCODE(),
                    CustomizedException.ENCRYPT_EXCEPTION.getMESSAGE());
        }
    }

    /**
     * AES加密
     *
     * @param clearText
     * @param secretKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     * @throws EncryptException
     */
    private static String aesEncrypt(String clearText, String secretKey)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException,
            UnsupportedEncodingException, EncryptException {
        /**
         * 参考：<a href="https://www.cnblogs.com/lingyejun/p/10971308.html"></a>
         */
        try {
            Cipher cipher = Cipher.getInstance(neteaseConfig.getDefaultKeyAlgorithm());
            byte[] raw = secretKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, neteaseConfig.getKeyAlgorithm());
            IvParameterSpec iv = new IvParameterSpec(neteaseConfig.getIvParameter().getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(clearText.getBytes(neteaseConfig.getCharset()));
            return new BASE64Encoder().encode(encrypted);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw e;
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            throw e;
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw e;
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
            throw e;
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            throw e;
        } catch (BadPaddingException e) {
            e.printStackTrace();
            throw e;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new EncryptException(CustomizedException.ENCRYPT_EXCEPTION.getCODE(),
                    CustomizedException.ENCRYPT_EXCEPTION.getMESSAGE());
        }
    }

    /**
     * RSA加密
     *
     * @param secretKey
     * @return
     * @throws EncryptException
     */
    private static String rsaEncrypt(String secretKey) throws EncryptException {
        try {
            secretKey = new StringBuilder(secretKey).reverse().toString();
            String hexString = DataUtil.string2HexString(secretKey);
            BigInteger hexStringBigInteger = DataUtil.string2DecBigInteger(hexString);
            int pubKeyBigInteger = DataUtil.string2DecInteger(neteaseConfig.getPubKey());
            BigInteger modulusBigInteger = DataUtil.string2DecBigInteger(neteaseConfig.getModules());
            BigInteger decBigInteger = hexStringBigInteger.pow(pubKeyBigInteger).mod(modulusBigInteger);
            String anotherHexString = DataUtil.decBigInteger2HexString(decBigInteger);
            String result = appendAtTheEndAtTheHead(anotherHexString, 256 - anotherHexString.length(), '0');
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new EncryptException(CustomizedException.ENCRYPT_EXCEPTION.getCODE(),
                    CustomizedException.ENCRYPT_EXCEPTION.getMESSAGE());
        }
    }

    /**
     * 在字符串头部补足一定长度的字符
     *
     * @param oldString
     * @param filledLength
     * @param filledCharacter
     * @return
     * @throws EncryptException
     */
    private static String appendAtTheEndAtTheHead(String oldString, int filledLength, char filledCharacter)
            throws EncryptException {
        try {
            StringBuilder builder = new StringBuilder(oldString);

            for (int i = 0; i < filledLength; i++) {
                builder.insert(0, filledCharacter);
            }
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new EncryptException(CustomizedException.ENCRYPT_EXCEPTION.getCODE(),
                    CustomizedException.ENCRYPT_EXCEPTION.getMESSAGE());
        }
    }

    /**
     * 获取加密后的参数
     *
     * @param cipherText
     * @return
     * @throws EncryptException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static String[] getParam(String cipherText)
            throws EncryptException, NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException, UnsupportedEncodingException,
            BadPaddingException, IllegalBlockSizeException {
        String params;
        String encSecKey;

        try {
//        String secretKey = getSecretKey(16);
            String secretKey = neteaseConfig.getNonce();
            params = aesEncrypt(aesEncrypt(cipherText, secretKey), neteaseConfig.getNonce());
            encSecKey = rsaEncrypt(secretKey);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            throw e;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw e;
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
            throw e;
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw e;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw e;
        } catch (BadPaddingException e) {
            e.printStackTrace();
            throw e;
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new EncryptException(CustomizedException.ENCRYPT_EXCEPTION.getCODE(),
                    CustomizedException.ENCRYPT_EXCEPTION.getMESSAGE());
        }
        return new String[]{params, encSecKey};
    }

    @Autowired
    public void setNeteaseConfig(NeteaseConfig neteaseConfig) {
        EncryptUtil.neteaseConfig = neteaseConfig;
    }
}
