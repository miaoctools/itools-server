package com.miaoc.itools.utils;

import com.alibaba.fastjson.JSONObject;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *  AES加密解密
 */
@Service
public class AESUtils {

    //使用AES-128-CBC加密模式，key需要为16位,key和iv可以相同！
    private static String KEY = "F8DA501C654257CD";
    private static String IV = "B99657160E15F1B0";
    private static String cipherStr = "AES/CBC/NoPadding";//"算法/模式/补码方式"NoPadding PKCS5Padding
//    private static String cipherStr = "AES/CBC/PKCS5Padding";//"算法/模式/补码方式"NoPadding PKCS5Padding
    /**
     *  AES加密
     */
    public static JSONObject encrypt(String data) throws Exception {
        try {
            String  key=KEY;
            String iv=IV;
            Cipher cipher = Cipher.getInstance(cipherStr);
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = data.getBytes("utf-8");
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
            JSONObject ret = new JSONObject();
            ret.put("data",new Base64().encodeToString(encrypted));
//            ret.put("data",parseByte2HexStr(encrypted));
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     *  AES解密
     */
    public static String desEncrypt(String data) throws Exception {
        try {
            String key=KEY;
            String iv=IV;
            byte[] encrypted1 = new Base64().decode(data);
            Cipher cipher = Cipher.getInstance(cipherStr);
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    /**
     * 十六进制转换字符串
     *
     * @param hexStr str Byte字符串(Byte之间无分隔符 如:[616C6B])
     * @return String 对应的字符串
     */
    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

}