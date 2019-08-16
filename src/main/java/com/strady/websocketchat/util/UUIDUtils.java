package com.strady.websocketchat.util;

import java.util.Random;
import java.util.UUID;

/**
 * @Author: strady
 * @Date: 2019-08-15
 * @Time: 20:37:24
 * @Description: UUID工具类
 */
public class UUIDUtils {

    /**
     * 取值用的字符串
     */
    private final static String __randChars = "0123456789abcdefghigklmnopqrstuvtxyzABCDEFGHIGKLMNOPQRSTUVWXYZ";

    private final static Random __random;

    static {
        /**
         * 将当前时间戳作为种子生成随机数
         */
        __random = new Random(System.currentTimeMillis());
    }

    /**
     * 使用JDK的UUID生成ID(主键)，并替换掉“-”，生成32位字符串
     *
     * @return
     */
    public static String UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成随机字符串
     *
     * @param length 字符串长度
     * @param isNum  是否仅数字
     * @return
     */
    public static String randomStr(int length, boolean isNum) {
        //根据isNum判断字符串中取值范围
        int size = isNum ? 10 : 62;
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            /**
             * 遍历length次，每次从size中随机取一个数，
             * 再在取值字符串中，取得对应位置的字符，
             * 最后拼接好指定长度的字符串
             */
            builder.append(__randChars.charAt(__random.nextInt(size)));
        }
        return builder.toString();
    }
}