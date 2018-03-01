package com.sai628.androidutils.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


/**
 * @author Sai
 * @ClassName: EncryptUtils
 * @Description: 加密解密工具类
 * @date 28/02/2018 11:28
 */
public class EncryptUtils
{
    private EncryptUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class.");
    }


    /*********************** 哈希加密相关 ***********************/
    /**
     * MD2加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptMD2ToString(String data)
    {
        return encryptMD2ToString(data.getBytes());
    }


    /**
     * MD2加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptMD2ToString(byte[] data)
    {
        byte[] cipher = encryptMD2(data);
        return ConvertUtils.bytes2HexString(cipher);
    }


    /**
     * MD2加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptMD2(byte[] data)
    {
        return hashTemplate(data, "MD2");
    }


    /**
     * MD5加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptMD5ToString(String data)
    {
        return encryptMD5ToString(data.getBytes());
    }


    /**
     * MD5加密
     *
     * @param data 明文字符串
     * @param salt 盐
     * @return 16进制加盐密文
     */
    public static String encryptMD5ToString(String data, String salt)
    {
        byte[] cipher = encryptMD5((data + salt).getBytes());
        return ConvertUtils.bytes2HexString(cipher);
    }


    /**
     * MD5加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptMD5ToString(byte[] data)
    {
        byte[] cipher = encryptMD5(data);
        return ConvertUtils.bytes2HexString(cipher);
    }


    /**
     * MD5加密
     *
     * @param data 明文字节数组
     * @param salt 盐字节数组
     * @return 16进制加盐密文
     */
    public static String encryptMD5ToString(byte[] data, byte[] salt)
    {
        if (data == null || data.length == 0 || salt == null || salt.length == 0)
        {
            return null;
        }

        byte[] dataWithSalt = new byte[data.length + salt.length];
        System.arraycopy(data, 0, dataWithSalt, 0, data.length);
        System.arraycopy(salt, 0, dataWithSalt, data.length, salt.length);

        byte[] cipher = encryptMD5(dataWithSalt);
        return ConvertUtils.bytes2HexString(cipher);
    }


    /**
     * MD5加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptMD5(byte[] data)
    {
        return hashTemplate(data, "MD5");
    }


    /**
     * MD5加密文件(实质为做散列计算)
     *
     * @param filePath 文件路径
     * @return 文件的16进制MD5校验码
     */
    public static String encryptMD5FileToString(String filePath)
    {
        File file = FileUtils.getFileByPath(filePath);
        return encryptMD5FileToString(file);
    }


    /**
     * MD5加密文件(实质为做散列计算)
     *
     * @param file 文件
     * @return 文件的16进制MD5校验码
     */
    public static String encryptMD5FileToString(File file)
    {
        byte[] cipher = encryptMD5File(file);
        return ConvertUtils.bytes2HexString(cipher);
    }


    /**
     * MD5加密文件(实质为做散列计算)
     *
     * @param filePath 文件路径
     * @return 文件的MD5校验码字节数组
     */
    public static byte[] encryptMD5File(String filePath)
    {
        File file = FileUtils.getFileByPath(filePath);
        return encryptMD5File(file);
    }


    /**
     * MD5加密文件(实质为做散列计算)
     *
     * @param file 文件
     * @return 文件的MD5校验码字节数组
     */
    public static byte[] encryptMD5File(File file)
    {
        if (file == null || !file.exists())
        {
            return null;
        }

        FileInputStream fis = null;
        DigestInputStream dis = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            fis = new FileInputStream(file);
            dis = new DigestInputStream(fis, md);
            byte[] buffer = new byte[256 * 1024];
            while (dis.read(buffer) > 0)
            {
            }

            md = dis.getMessageDigest();
            return md.digest();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            CloseUtils.closeIO(fis, dis);
        }
    }


    /**
     * SHA1加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA1ToString(String data)
    {
        return encryptSHA1ToString(data.getBytes());
    }


    /**
     * SHA1加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA1ToString(byte[] data)
    {
        byte[] cipher = encryptSHA1(data);
        return ConvertUtils.bytes2HexString(cipher);
    }


    /**
     * SHA1加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA1(byte[] data)
    {
        return hashTemplate(data, "SHA1");
    }


    /**
     * SHA224加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA224ToString(String data)
    {
        return encryptSHA224ToString(data.getBytes());
    }


    /**
     * SHA224加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA224ToString(byte[] data)
    {
        byte[] cipher = encryptSHA224(data);
        return ConvertUtils.bytes2HexString(cipher);
    }


    /**
     * SHA224加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA224(byte[] data)
    {
        return hashTemplate(data, "SHA224");
    }


    /**
     * SHA256加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA256ToString(String data)
    {
        return encryptSHA256ToString(data.getBytes());
    }


    /**
     * SHA256加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA256ToString(byte[] data)
    {
        byte[] cipher = encryptSHA256(data);
        return ConvertUtils.bytes2HexString(cipher);
    }


    /**
     * SHA256加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA256(byte[] data)
    {
        return hashTemplate(data, "SHA256");
    }


    /**
     * SHA384加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA384ToString(String data)
    {
        return encryptSHA384ToString(data.getBytes());
    }


    /**
     * SHA384加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA384ToString(byte[] data)
    {
        byte[] cipher = encryptSHA384(data);
        return ConvertUtils.bytes2HexString(cipher);
    }


    /**
     * SHA384加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA384(byte[] data)
    {
        return hashTemplate(data, "SHA384");
    }


    /**
     * SHA512加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA512ToString(String data)
    {
        return encryptSHA512ToString(data.getBytes());
    }


    /**
     * SHA512加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA512ToString(byte[] data)
    {
        byte[] cipher = encryptSHA512(data);
        return ConvertUtils.bytes2HexString(cipher);
    }


    /**
     * SHA512加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA512(byte[] data)
    {
        return hashTemplate(data, "SHA512");
    }


    /**
     * hash加密模板
     *
     * @param data      数据
     * @param algorithm 加密算法
     * @return 密文字节数组
     */
    public static byte[] hashTemplate(byte[] data, String algorithm)
    {
        if (data == null || data.length == 0)
        {
            return null;
        }

        try
        {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(data);
            return md.digest();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Hmac-MD5加密
     *
     * @param data 明文字符串
     * @param key  秘钥
     * @return 16进制密文
     */
    public static String encryptHmacMD5ToString(String data, String key)
    {
        return encryptHmacMD5ToString(data.getBytes(), key.getBytes());
    }


    /**
     * Hmac-MD5加密
     *
     * @param data 明文字节数组
     * @param key  秘钥
     * @return 16进制密文
     */
    public static String encryptHmacMD5ToString(byte[] data, byte[] key)
    {
        byte[] cipher = encryptHmacMD5(data, key);
        return ConvertUtils.bytes2HexString(cipher);
    }


    /**
     * Hmac-MD5加密
     *
     * @param data 明文字节数组
     * @param key  秘钥
     * @return 密文字节数组
     */
    public static byte[] encryptHmacMD5(byte[] data, byte[] key)
    {
        return hmacTemplate(data, key, "HmacMD5");
    }


    /**
     * Hmac-SHA1加密
     *
     * @param data 明文字符串
     * @param key  秘钥
     * @return 16进制密文
     */
    public static String encryptHmacSHA1ToString(String data, String key)
    {
        return encryptHmacSHA1ToString(data.getBytes(), key.getBytes());
    }


    /**
     * Hmac-SHA1加密
     *
     * @param data 明文字节数组
     * @param key  秘钥
     * @return 16进制密文
     */
    public static String encryptHmacSHA1ToString(byte[] data, byte[] key)
    {
        byte[] cipher = encryptHmacSHA1(data, key);
        return ConvertUtils.bytes2HexString(cipher);
    }


    /**
     * Hmac-SHA1加密
     *
     * @param data 明文字节数组
     * @param key  秘钥
     * @return 密文字节数组
     */
    public static byte[] encryptHmacSHA1(byte[] data, byte[] key)
    {
        return hmacTemplate(data, key, "HmacSHA1");
    }


    /**
     * Hmac-SHA224加密
     *
     * @param data 明文字符串
     * @param key  秘钥
     * @return 16进制密文
     */
    public static String encryptHmacSHA224ToString(String data, String key)
    {
        return encryptHmacSHA224ToString(data.getBytes(), key.getBytes());
    }


    /**
     * Hmac-SHA224加密
     *
     * @param data 明文字节数组
     * @param key  秘钥
     * @return 16进制密文
     */
    public static String encryptHmacSHA224ToString(byte[] data, byte[] key)
    {
        byte[] cipher = encryptHmacSHA224(data, key);
        return ConvertUtils.bytes2HexString(cipher);
    }


    /**
     * Hmac-SHA224加密
     *
     * @param data 明文字节数组
     * @param key  秘钥
     * @return 密文字节数组
     */
    public static byte[] encryptHmacSHA224(byte[] data, byte[] key)
    {
        return hmacTemplate(data, key, "HmacSHA224");
    }


    /**
     * Hmac-SHA256加密
     *
     * @param data 明文字符串
     * @param key  秘钥
     * @return 16进制密文
     */
    public static String encryptHmacSHA256ToString(String data, String key)
    {
        return encryptHmacSHA256ToString(data.getBytes(), key.getBytes());
    }


    /**
     * Hmac-SHA256加密
     *
     * @param data 明文字节数组
     * @param key  秘钥
     * @return 16进制密文
     */
    public static String encryptHmacSHA256ToString(byte[] data, byte[] key)
    {
        byte[] cipher = encryptHmacSHA256(data, key);
        return ConvertUtils.bytes2HexString(cipher);
    }


    /**
     * Hmac-SHA256加密
     *
     * @param data 明文字节数组
     * @param key  秘钥
     * @return 密文字节数组
     */
    public static byte[] encryptHmacSHA256(byte[] data, byte[] key)
    {
        return hmacTemplate(data, key, "HmacSHA256");
    }


    /**
     * Hmac-SHA384加密
     *
     * @param data 明文字符串
     * @param key  秘钥
     * @return 16进制密文
     */
    public static String encryptHmacSHA384ToString(String data, String key)
    {
        return encryptHmacSHA384ToString(data.getBytes(), key.getBytes());
    }


    /**
     * Hmac-SHA384加密
     *
     * @param data 明文字节数组
     * @param key  秘钥
     * @return 16进制密文
     */
    public static String encryptHmacSHA384ToString(byte[] data, byte[] key)
    {
        byte[] cipher = encryptHmacSHA384(data, key);
        return ConvertUtils.bytes2HexString(cipher);
    }


    /**
     * Hmac-SHA384加密
     *
     * @param data 明文字节数组
     * @param key  秘钥
     * @return 密文字节数组
     */
    public static byte[] encryptHmacSHA384(byte[] data, byte[] key)
    {
        return hmacTemplate(data, key, "HmacSHA384");
    }


    /**
     * Hmac-SHA512加密
     *
     * @param data 明文字符串
     * @param key  秘钥
     * @return 16进制密文
     */
    public static String encryptHmacSHA512ToString(String data, String key)
    {
        return encryptHmacSHA512ToString(data.getBytes(), key.getBytes());
    }


    /**
     * Hmac-SHA512加密
     *
     * @param data 明文字节数组
     * @param key  秘钥
     * @return 16进制密文
     */
    public static String encryptHmacSHA512ToString(byte[] data, byte[] key)
    {
        byte[] cipher = encryptHmacSHA512(data, key);
        return ConvertUtils.bytes2HexString(cipher);
    }


    /**
     * Hmac-SHA512加密
     *
     * @param data 明文字节数组
     * @param key  秘钥
     * @return 密文字节数组
     */
    public static byte[] encryptHmacSHA512(byte[] data, byte[] key)
    {
        return hmacTemplate(data, key, "HmacSHA512");
    }


    /**
     * Hmac加密模版
     *
     * @param data      数据
     * @param key       秘钥
     * @param algorithm 加密算法
     * @return 密文字节数组
     */
    public static byte[] hmacTemplate(byte[] data, byte[] key, String algorithm)
    {
        if (data == null || data.length == 0 || key == null || key.length == 0)
        {
            return null;
        }

        try
        {
            SecretKeySpec secretKey = new SecretKeySpec(key, algorithm);
            Mac mac = Mac.getInstance(algorithm);
            mac.init(secretKey);
            return mac.doFinal(data);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (InvalidKeyException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    /************************ DES加密相关 ***********************/
    /**
     * DES转换
     * <p>算法名称/加密模式/填充方式</p>
     * <p>加密模式有: 电子密码本模式ECB  加密块链模式CBC  加密反馈模式CFB  输出反馈模式OFB</p>
     * <p>填充方式有: NoPadding  ZerosPadding  PKCS5Padding</p>
     */
    public static String DES_Transformation = "DES/ECB/NoPadding";
    private static final String DES_Algorithm = "DES";


    /**
     * DES加密后转为Base64编码
     *
     * @param data 明文字节数组
     * @param key  8字节秘钥
     * @return Base64密文
     */
    public static byte[] encryptDESToBase64(byte[] data, byte[] key)
    {
        byte[] cipher = encryptDES(data, key);
        return EncodeUtils.base64Encode(cipher);
    }


    /**
     * DES加密后转为16进制
     *
     * @param data 明文字节数组
     * @param key  8字节秘钥
     * @return 16进制密文
     */
    public static String encryptDESToHexString(byte[] data, byte[] key)
    {
        byte[] cipher = encryptDES(data, key);
        return ConvertUtils.bytes2HexString(cipher);
    }


    /**
     * DES加密
     *
     * @param data 明文字节数组
     * @param key  8字节秘钥
     * @return 密文字节数组
     */
    public static byte[] encryptDES(byte[] data, byte[] key)
    {
        return desTemplate(data, key, DES_Algorithm, DES_Transformation, true);
    }


    /**
     * DES解密Base64编码密文
     *
     * @param data Base64编码密文
     * @param key  8字节秘钥
     * @return 明文字节数组
     */
    public static byte[] decryptBase64ViaDES(byte[] data, byte[] key)
    {
        byte[] bytes = EncodeUtils.base64Decode(data);
        return decryptDES(bytes, key);
    }


    /**
     * DES解密16进制密文
     *
     * @param data 16进制密文
     * @param key  8字节秘钥
     * @return 明文字节数组
     */
    public static byte[] decryptHexStringViaDES(String data, byte[] key)
    {
        byte[] bytes = ConvertUtils.hexString2Bytes(data);
        return decryptDES(bytes, key);
    }


    /**
     * DES解密
     *
     * @param data 密文字节数组
     * @param key  8字节秘钥
     * @return 明文字节数组
     */
    public static byte[] decryptDES(byte[] data, byte[] key)
    {
        return desTemplate(data, key, DES_Algorithm, DES_Transformation, false);
    }


    /************************ 3DES加密相关 ***********************/
    /**
     * 3DES转换
     * <p>算法名称/加密模式/填充方式</p>
     * <p>加密模式有: 电子密码本模式ECB  加密块链模式CBC  加密反馈模式CFB  输出反馈模式OFB</p>
     * <p>填充方式有: NoPadding  ZerosPadding  PKCS5Padding</p>
     */
    public static String TripleDES_Transformation = "DESede/ECB/NoPadding";
    private static final String TripleDES_Algorithm = "DESede";


    /**
     * 3DES加密后转为Base64编码
     *
     * @param data 明文字节数组
     * @param key  24字节秘钥
     * @return Base64密文
     */
    public static byte[] encrypt3DESToBase64(byte[] data, byte[] key)
    {
        byte[] cipher = encrypt3DES(data, key);
        return EncodeUtils.base64Encode(cipher);
    }


    /**
     * 3DES加密后转为16进制
     *
     * @param data 明文字节数组
     * @param key  24字节秘钥
     * @return 16进制密文
     */
    public static String encrypt3DESToHexString(byte[] data, byte[] key)
    {
        byte[] cipher = encrypt3DES(data, key);
        return ConvertUtils.bytes2HexString(cipher);
    }


    /**
     * 3DES加密
     *
     * @param data 明文字节数组
     * @param key  24字节秘钥
     * @return 密文字节数组
     */
    public static byte[] encrypt3DES(byte[] data, byte[] key)
    {
        return desTemplate(data, key, TripleDES_Algorithm, TripleDES_Transformation, true);
    }


    /**
     * 3DES解密Base64编码密文
     *
     * @param data Base64编码密文
     * @param key  24字节秘钥
     * @return 明文字节数组
     */
    public static byte[] decryptBase64Via3DES(byte[] data, byte[] key)
    {
        byte[] bytes = EncodeUtils.base64Decode(data);
        return decrypt3DES(bytes, key);
    }


    /**
     * 3DES解密16进制密文
     *
     * @param data 16进制密文
     * @param key  24字节秘钥
     * @return 明文字节数组
     */
    public static byte[] decryptHexStringVia3DES(String data, byte[] key)
    {
        byte[] bytes = ConvertUtils.hexString2Bytes(data);
        return decrypt3DES(bytes, key);
    }


    /**
     * 3DES解密
     *
     * @param data 密文字节数组
     * @param key  24字节秘钥
     * @return 明文字节数组
     */
    public static byte[] decrypt3DES(byte[] data, byte[] key)
    {
        return desTemplate(data, key, TripleDES_Algorithm, TripleDES_Transformation, false);
    }


    /************************ AES加密相关 ***********************/
    public static String AES_Transformation = "AES/ECB/NoPadding";
    private static final String AES_Algorithm = "AES";


    /**
     * AES加密后转为Base64编码
     *
     * @param data 明文字节数组
     * @param key  16/24/32字节秘钥
     * @return Base64密文
     */
    public static byte[] encryptAESToBase64(byte[] data, byte[] key)
    {
        byte[] cipher = encryptAES(data, key);
        return EncodeUtils.base64Encode(cipher);
    }


    /**
     * AES加密后转为16进制
     *
     * @param data 明文字节数组
     * @param key  16/24/32字节秘钥
     * @return 16进制密文
     */
    public static String encryptAESToHexString(byte[] data, byte[] key)
    {
        byte[] cipher = encryptAES(data, key);
        return ConvertUtils.bytes2HexString(cipher);
    }


    /**
     * AES加密
     *
     * @param data 明文字节数组
     * @param key  16/24/32字节秘钥
     * @return 密文字节数组
     */
    public static byte[] encryptAES(byte[] data, byte[] key)
    {
        return desTemplate(data, key, AES_Algorithm, AES_Transformation, true);
    }


    /**
     * AES解密Base64编码密文
     *
     * @param data Base64编码密文
     * @param key  16/24/32字节秘钥
     * @return 明文字节数组
     */
    public static byte[] decryptBase64ViaAES(byte[] data, byte[] key)
    {
        byte[] bytes = EncodeUtils.base64Decode(data);
        return decryptAES(bytes, key);
    }


    /**
     * AES解密16进制密文
     *
     * @param data 16进制密文
     * @param key  16/24/32字节秘钥
     * @return 明文字节数组
     */
    public static byte[] decryptHexStringViaAES(String data, byte[] key)
    {
        byte[] bytes = ConvertUtils.hexString2Bytes(data);
        return decryptAES(bytes, key);
    }


    /**
     * AES解密
     *
     * @param data 密文字节数组
     * @param key  16/24/32字节秘钥
     * @return 明文字节数组
     */
    public static byte[] decryptAES(byte[] data, byte[] key)
    {
        return desTemplate(data, key, AES_Algorithm, AES_Transformation, false);
    }


    /**
     * DES加密模板(适用于DES/3DES/AES)
     *
     * @param data           数据(明文/密文字节数组)
     * @param key            秘钥
     * @param algorithm      加密算法
     * @param transformation 转换
     * @param isEncrypt      {@code true}: 加密 {@code false}: 解密
     * @return 密文/明文字节数组
     */
    public static byte[] desTemplate(byte[] data, byte[] key, String algorithm, String transformation, boolean isEncrypt)
    {
        if (data == null || data.length == 0 || key == null || key.length == 0)
        {
            return null;
        }

        try
        {
            SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
            Cipher cipher = Cipher.getInstance(transformation);
            SecureRandom random = new SecureRandom();
            cipher.init(isEncrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, keySpec, random);
            return cipher.doFinal(data);
        }
        catch (Throwable e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
