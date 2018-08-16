package cn.coderme.cblog.utils;

import java.util.Random;

/**
 * 验证码生成器
 * Created By zzitbar
 * Date:2018/8/15
 * Time:10:34
 */
public class ValidateCodeUtils {

    private static final int codeCount = 6;//定义验证码的个数

    private static final char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * 生成验证码
     * @return
     */
    public static String generate() {
        StringBuffer randomCode = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码
            String code = String.valueOf(codeSequence[random.nextInt(62)]);
            randomCode.append(code);
        }
        return randomCode.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i<20; i++) {
            System.out.println(ValidateCodeUtils.generate());
        }
    }
}