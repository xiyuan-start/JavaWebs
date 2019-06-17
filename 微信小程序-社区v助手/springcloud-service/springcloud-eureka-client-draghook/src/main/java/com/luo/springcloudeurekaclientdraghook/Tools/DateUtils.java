package com.luo.springcloudeurekaclientdraghook.Tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String parse(Date date) {

    return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ").format(date);
    }
}