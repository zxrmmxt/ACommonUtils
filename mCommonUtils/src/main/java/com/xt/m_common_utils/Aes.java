package com.xt.m_common_utils;

import android.util.Base64;

import com.blankj.utilcode.util.EncodeUtils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Administrator on 2017/4/28.
 * AES加密解密
 */

public class Aes {

    /**
     * "AES/CBC/PKCS5Padding"
     * mode 加密模式 CBC
     * pad 填充方式 PKCS5Padding
     */
    public static String encryptAES(String content, String key)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, UnsupportedEncodingException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        byte[] byteContent = content.getBytes("UTF-8");

        // 注意，为了能与 iOS 统一
        // 这里的 key 不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
        byte[] enCodeFormat = key.getBytes("utf-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");

        byte[] initParam = key.getBytes("utf-8");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

        // 指定加密的算法、工作模式和填充方式
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] encryptedBytes = cipher.doFinal(byteContent);

        // 同样对加密后数据进行 base64 编码
        return EncodeUtils.base64Encode2String(encryptedBytes);
    }

    /**
     * "AES/CBC/NoPadding"
     * mode 加密模式 CBC
     * pad 填充方式 NoPadding
     */
    public static String encrypt(String data, String key, String iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return EncodeUtils.base64Encode2String(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encrypt(String data, String aesKey) {
        String key = aesKey;
        String iv = aesKey;
        return encrypt(data, key, iv);
    }


    public static String decrypt(String data, String key, String iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            SecretKeySpec keyspec = new SecretKeySpec(fullZore(key, blockSize), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(fullZore(iv, blockSize));
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] decrypted = cipher.doFinal(Base64.decode(data, Base64.DEFAULT));
            return new String(decrypted).trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static byte[] fullZore(String data, int blockSize) {
        byte[] dataBytes = data.getBytes();
        int plaintextLength = dataBytes.length;
        if (plaintextLength % blockSize != 0) {
            plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
        }
        byte[] plaintext = new byte[plaintextLength];
        System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
        return plaintext;
    }
}
