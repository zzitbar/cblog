package cn.coderme.cblog.utils;

import java.security.MessageDigest;

public class Md5Utils {

    public static String getMD5ofStr(String origString) {
        return getMD5ofStr(origString, null);
    }
        /**
         * 获得MD5加密密码的方法
         */
    public static String getMD5ofStr(String origString, String salt) {  
        String origMD5 = null;  
        try {  
            MessageDigest md5 = MessageDigest.getInstance("MD5");  
            if (salt != null) {
            	md5.reset();
            	md5.update(salt.getBytes("UTF-8"));
            }
            byte[] result = md5.digest(origString.getBytes("UTF-8"));
            origMD5 = byteArray2HexStr(result);  
        } catch (Exception e) {  
            e.printStackTrace();
        }  
        return origMD5;  
    }  
    /** 
     * 处理字节数组得到MD5密码的方法 
     */  
    private static String byteArray2HexStr(byte[] bs) {  
        StringBuffer sb = new StringBuffer();
        for (byte b : bs) {  
        sb.append(byte2HexStr(b));
        }  
        return sb.toString();  
    }  
    /** 
     * 字节标准移位转十六进制方法 
     */  
    private static String byte2HexStr(byte b) {  
        String hexStr = null;  
        int n = b;  
        if (n < 0) {  
            // 若需要自定义加密,请修改这个移位算法即可  
            n = b & 0x7F + 128;  
        }  
        hexStr = Integer.toHexString(n / 16) + Integer.toHexString(n % 16);  
        return hexStr;
    }  
    /** 
     * 提供一个MD5多次加密方法 
     */  
    public static String getMD5ofStr(String origString, String salt, int times) {  
        String md5 = getMD5ofStr(origString, salt);  
        for (int i = 0; i < times - 1; i++) {  
            md5 = getMD5ofStr(md5, salt);  
        }  
        return getMD5ofStr(md5, salt);  
    }  
    /** 
     * 密码验证方法 
     */  
    public static boolean verifyPassword(String inputStr, String salt, String MD5Code) {  
        return getMD5ofStr(inputStr, salt).equals(MD5Code);  
    }  
    /** 
     * 多次加密时的密码验证方法 
     */  
    public static boolean verifyPassword(String inputStr, String salt, String MD5Code,  
            int times) {  
        return getMD5ofStr(inputStr, salt, times).equals(MD5Code);  
    }  
}
