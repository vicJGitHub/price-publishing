package com.hywa.pricepublish.common.utils;

import java.util.Random;

public class IDUtils {
    /**
     *
     * @Title: genItemId  生成随机数,用作查询码
     *@author vic
     * @Description:
     * @param @return
     * @return long
     * @throws
     */
    public static long getSearchId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        long id = new Long(str);
        return id;
    }


}
