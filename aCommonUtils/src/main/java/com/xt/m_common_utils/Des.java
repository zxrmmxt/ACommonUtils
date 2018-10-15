package com.xt.m_common_utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


/**
 * Author: Matt
 * Date: 2015/12/30
 */
public class Des {

    private final static String ENCRYPT_KEY = "34567891";

    public static String encryptDES(String encryptString) {
        String encryptKey = ENCRYPT_KEY;

        try {
            //返回实现指定转换的 Cipher 对象   “算法/模式/填充”
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            //创建一个 DESKeySpec 对象，使用 8 个字节的key作为 DES 密钥的密钥内容。
            DESKeySpec desKeySpec = new DESKeySpec(encryptKey.getBytes("UTF-8"));
            //返回转换指定算法的秘密密钥的 SecretKeyFactory 对象。
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            //根据提供的密钥生成 SecretKey 对象。
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            //使用 iv 中的字节作为 IV 来构造一个 IvParameterSpec 对象。复制该缓冲区的内容来防止后续修改。
            IvParameterSpec iv = new IvParameterSpec(encryptKey.getBytes());
            //用密钥和一组算法参数初始化此 Cipher;Cipher：加密、解密、密钥包装或密钥解包，具体取决于 opmode 的值。
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

            byte[] encryptedData = cipher.doFinal(encryptString.getBytes());

            return toHexString(encryptedData).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String toHexString(byte[] data) {
        StringBuilder hex = new StringBuilder();
        for (byte b : data) {
            String plainText = Integer.toHexString(b & 0xFF);
            if (plainText.length() < 2) {
                plainText = "0" + plainText;
            }
            hex.append(plainText);
        }
        return hex.toString();
    }

    /**
     * 解密
     *
     * @param content
     *            待解密内容
     * @param key
     *            解密的密钥
     * @return
     */
    public static String decrypt(byte[] content, String key) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
            byte[] result = cipher.doFinal(content);
            return new String(result);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /*/// <summary>
    /// DES 解密
    /// </summary>
    /// <param name="pToDecrypt">被解密的字符串</param>
    /// <returns>返回被解密的字符串</returns>
    public static string DESDecrypt(string pToDecrypt, string sKey)
    {
        try
        {
            DESCryptoServiceProvider des = new DESCryptoServiceProvider();

            byte[] inputByteArray = new byte[pToDecrypt.Length / 2];
            for (int x = 0; x < pToDecrypt.Length / 2; x++)
            {
                int i = (Convert.ToInt32(pToDecrypt.Substring(x * 2, 2), 16));
                inputByteArray[x] = (byte)i;
            }

            des.Key = ASCIIEncoding.ASCII.GetBytes(sKey);　//建立加密对象的密钥和偏移量，此值重要，不能修改
            des.IV = ASCIIEncoding.ASCII.GetBytes(sKey);
            MemoryStream ms = new MemoryStream();
            CryptoStream cs = new CryptoStream(ms, des.CreateDecryptor(), CryptoStreamMode.Write);

            cs.Write(inputByteArray, 0, inputByteArray.Length);
            cs.FlushFinalBlock();

            //建立StringBuild对象，createDecrypt使用的是流对象，必须把解密后的文本变成流对象
            StringBuilder ret = new StringBuilder();

            return System.Text.Encoding.Default.GetString(ms.ToArray());
        }
        catch (Exception ex)
        {
            LogManage.LogDebug(ex);
        }
        return "";
    }*/

    //解密数据
    public static String decrypt(String message, String key) {
        byte[] bytesrc = DigitalTrans.hex2byte(message);
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        DESKeySpec desKeySpec = null;
        try {
            desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        SecretKeyFactory keyFactory = null;
        try {
            keyFactory = SecretKeyFactory.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SecretKey secretKey = null;
        try {
            secretKey = keyFactory.generateSecret(desKeySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        IvParameterSpec iv = null;
        try {
            iv = new IvParameterSpec(key.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        byte[] retByte = new byte[0];
        try {
            retByte = cipher.doFinal(bytesrc);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return new String(retByte);
    }


}
