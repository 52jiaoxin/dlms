package com.psx.server.config.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author psx
 * @date 2021/4/27 17:50
 */
public class StringUtils {
    public static String add(String str){
        System.out.println(str);
        String name[]=str.split("\\.");
        String fname[]=name[0].split("\\-");
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("HHmmss");
        String filename=fname[0]+"-"+sdf.format(date)+"."+name[1];
        return filename;
    }
}
