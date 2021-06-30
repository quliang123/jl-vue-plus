package com.jl.app.utils;

import java.text.SimpleDateFormat;
import java.util.*;

public class PayUtil {
    /**
     * 生成订单号
     *
     * @return
     */
    public static String getTradeNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }
        return newDate + result;
    }

}