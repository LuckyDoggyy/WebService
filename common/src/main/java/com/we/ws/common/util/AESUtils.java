package com.we.ws.common.util;

import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Created by twogoods on 16/10/11.
 */
public class AESUtils {
    /*
    * 此处使用AES-128-ECB加密模式，key需要为16位。
    */
    private static String KEY = "1292560490125586";

    //加密
    public static String Encrypt(String original) throws Exception {
        if(StringUtils.isEmpty(original)){
            return "";
        }
        byte[] raw = KEY.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(original.getBytes("utf-8"));
        return parseByte2HexStr(encrypted);
    }

    // 解密
    public static String Decrypt(String ciphertext) throws Exception {
        if(StringUtils.isEmpty(ciphertext)){
            return "";
        }
        byte[] content = parseHexStr2Byte(ciphertext);
        byte[] raw = KEY.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] original = cipher.doFinal(content);
        String originalString = new String(original, "utf-8");
        return originalString;
    }

    /**
     * 将二进制转换成16进制
     *
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
     *
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

    public static void main(String[] args) throws Exception {
        /*
         * 此处使用AES-128-ECB加密模式，key需要为16位。
         */
        String cKey = "123456789012345678";
        // 需要加密的字串
        String cSrc = System.currentTimeMillis()+"-1345-你哈哈";
        System.out.println(cSrc);
        // 加密
        String s = Encrypt(cSrc);
        System.out.println(s);

        // 解密
        String DeString = Decrypt("");
        System.out.println("解密后的字串是：" + DeString);
    }
}
