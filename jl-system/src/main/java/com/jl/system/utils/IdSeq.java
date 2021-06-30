package com.jl.system.utils;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * @program: jl-vue-plus
 * @description: 校友会规则生成序列
 * @author: liang qu
 * @create: 2021-06-18 15:52
 **/

public class IdSeq {

    //捐赠证书编号前缀
    private static final String DONATION_CERTIFICATE_NUMBER_PREFIX = "XMFLSEF";
    //拼接符号
    private static final String DELIMITER = "-";
    private static final String YY = "YY";
    private static final String YYYY = "YYYY";

    /**
     * 返回五位或者是六位序列
     * true   进行护照号规则
     * false 进行捐赠证书编号规则
     * @param isRule
     * @return
     */
    public static String generateSequence(boolean isRule) {
        StringBuilder sb = new StringBuilder(20);
        SimpleDateFormat sdf = null;
        String format;
        //是否进行护照号规则
        if (isRule) {
            sdf = new SimpleDateFormat(YY);
            format = sdf.format(System.currentTimeMillis());
            sb.append(format);
            sb.append(DELIMITER);
            int seq = generateSequence(10000);
            sb.append(seq);
        } else {
            sdf = new SimpleDateFormat(YYYY);
            format = sdf.format(System.currentTimeMillis());
            sb.append(DONATION_CERTIFICATE_NUMBER_PREFIX);
            sb.append(format);
            int seq = generateSequence(100000);
            sb.append(seq);
        }
        return sb.toString();
    }

    /**
     * 生成五位或者是六位数序列，不保证唯一序列
     * @param digits    通过digits数值来控制五位数或者是六位数
     * @return
     */
    private static int generateSequence(int digits) {
        Random r = new Random(System.currentTimeMillis());
        int seq = ((1 + r.nextInt(2)) * digits + r.nextInt(10000));
        if (44 == seq || 444 == seq || 4444 == seq || 4444 == seq) {
            return generateSequence(digits);
        }
        return seq;
    }
}
