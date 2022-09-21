package edu.zut.vlog.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/23
 * Time: 10:07
 **/
public class NowTime {
    public static final Timestamp getTime(){
        Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);//将时间格式转换成符合Timestamp要求的格式.
        //Date time=sdf.parse(nowTime);
        Timestamp dates = Timestamp.valueOf(nowTime);//把时间转换
        return dates;
    }

}
