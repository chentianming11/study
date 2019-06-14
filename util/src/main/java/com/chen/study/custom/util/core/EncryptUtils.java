package com.chen.study.custom.util.core;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @author 宋晓岳
 * @date 2018/8/30
 */
@UtilityClass
public class EncryptUtils {
    private final static String CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DEFAULT_AES_KEY_STR = "AxhjMVebmGiprTkbtjteeFhoipDmBhIF";
    private static final AES aes = new AES(DEFAULT_AES_KEY_STR);

    /**
     * 使用SHA-1 and Md5 with salt加密
     *
     * @param str 待加密串
     * @return 加密后的字符串
     */
    public static String encrypt(String str, String salt) {
        return shaEncode(md5Encode(str, salt));
    }

    /**
     * 使用Md5加密
     *
     * @param inStr
     * @param salt
     * @return
     */
    public static String md5Encode(String inStr, String salt) {
        return Md5Crypt.md5Crypt(inStr.getBytes(Charset.forName("utf8")),
                salt);
    }

    /***
     * SHA加密 生成40位SHA码
     *
     * @param inStr 待加密字符串
     * @return 返回40位SHA码
     */
    public static String shaEncode(String inStr) {
        byte[] bytes = DigestUtils.sha1(inStr);
        StringBuilder hexValue = new StringBuilder();
        for (byte shaByte : bytes) {
            int val = ((int) shaByte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 无盐md5加密
     *
     * @param str 待加密串
     * @return 加密后的串
     */
    public static String md5(String str) {
        try {
            return md5(str.getBytes("utf8"));
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 无盐md5加密
     *
     * @return 加密后的串
     */
    public static String md5(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(data);
            int i;
            StringBuilder buf = new StringBuilder(32);
            for (byte b : bytes) {
                i = b;
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 生成指定位数的随机字符串
     * @param bit
     * @return
     */
    public static String randomString(int bit) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bit; i++) {
            sb.append(CHARS.charAt(random.nextInt(bit)));
        }
        return sb.toString();
    }

    /**
     * aes加密
     *
     * @param content
     * @return
     */
    public static String aesEncrypt(String content) {
        try {
            return aes.encrypt(content);
        } catch (Exception e) {
            throw new AppException("加密失败", e);
        }
    }

    /**
     * aes加密
     *
     * @param id
     * @return
     */
    public static String aesEncrypt(Number id) {
        try {
            return aes.encrypt(String.valueOf(id));
        } catch (Exception e) {
            throw new AppException("加密失败", e);
        }
    }

    /**
     * aes解密
     *
     * @param content
     * @return
     */
    public static String aesDecrypt(String content) {
        try {
            return aes.decrypt(content);
        } catch (Exception e) {
            throw new AppException("解密失败", e);
        }
    }

    public static class AES {
        private static final String UTF_8 = "utf-8";
        /**
         * 密钥对象
         */
        private SecretKeySpec secretKeySpec;
        /**
         * 加密的Cipher
         */
        private Cipher encryptCipher;
        /**
         * 解密的Cipher
         */
        private Cipher decryptCipher;

        @SneakyThrows
        private AES(String keyStr) {
            //指定加密算法的名称为AES,
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //初始化密钥生成器，指定密钥的长度(单位:bit),
            //SecureRandom是生成安全随机数序列
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(keyStr.getBytes());
            keyGenerator.init(128, secureRandom);
            //生成原始对称密钥
            SecretKey secretKey = keyGenerator.generateKey();
            //返回编码格式的密钥
            byte[] enCodeFormat = secretKey.getEncoded();
            //根据字节数组生成AES专用密钥
            secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            //根据指定算法生成密码器
            encryptCipher = Cipher.getInstance("AES");
            decryptCipher = Cipher.getInstance("AES");
            //初始化密码器，
            // 第一个参数为密码的操作模式：加密(ENCRYPT_MODE),解密(DECRYPT_MODE)
            //第二个参数为AES密钥
            encryptCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            decryptCipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        }

        /**
         * 明文加密
         *
         * @param content 加密前的原内容
         * @return
         */
        public String encrypt(String content) throws UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
            //获取加密内容的字节数组
            byte[] contentBytes = content.getBytes(UTF_8);
            byte[] aesBytes = encryptCipher.doFinal(contentBytes);
            //为了避免解密时数据丢失，转成16进制
            return HexStringUtils.bytesToHexString(aesBytes);

        }

        /**
         * 密文解密
         *
         * @param content 加密后的内容
         * @return
         */
        public String decrypt(String content) throws BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
            byte[] aesBytes = HexStringUtils.hexStringToByte(content.toLowerCase());
            //将密文进行解密
            byte[] contentBytes = decryptCipher.doFinal(aesBytes);
            //将解密后的内容转成字符串并返回
            return new String(contentBytes, UTF_8);
        }
    }

}
