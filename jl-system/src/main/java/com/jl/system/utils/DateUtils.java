package com.jl.system.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 *
 * @author jl
 */
public class DateUtils {

    /**
     * 获取前N天的集合
     *
     * @param startTime 开始时间  可为null
     * @param endTime   结束时间  可为null
     * @param nday      前N天
     * @param patterns  时间格式
     * @return
     */
    public static List<String> getNDaysList(String startTime, String endTime, int nday, String patterns) {
        int ndaycurrent = nday - 1;
        // 返回的日期集合
        List<String> days = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat(patterns);
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -ndaycurrent);
            Date start = calendar.getTime();
            Date end = new Date();
            if (StringUtils.isNotBlank(startTime) && StringUtils.isBlank(endTime)) {
                //如果用户只选择了startTime,endTime为null,startTime + 10的日期
                start = dateFormat.parse(startTime);
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(start);
                calendar1.add(Calendar.DATE, ndaycurrent);
                end = calendar1.getTime();
            } else if (StringUtils.isBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                //如果用户只选择了endTime,startTime为null,endTime - 10的日期
                end = dateFormat.parse(endTime);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(end);
                calendar2.add(Calendar.DATE, -ndaycurrent);
                start = calendar2.getTime();
            } else if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                //如果用户选择了startTime和endTime，判断endTime - startTime两个日期是否超过了ndaycurrent，超过返回最近nday天记录
                Date start1 = dateFormat.parse(startTime);
                Date end1 = dateFormat.parse(endTime);
                int a = (int) ((end1.getTime() - start1.getTime()) / (1000 * 3600 * 24));
                if (a <= ndaycurrent) {
                    //如果小于等于n天
                    start = dateFormat.parse(startTime);
                    end = dateFormat.parse(endTime);
                }
            }
            //如果超过了ndaycurrent天,就是默认的start和end
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)

            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }
}
