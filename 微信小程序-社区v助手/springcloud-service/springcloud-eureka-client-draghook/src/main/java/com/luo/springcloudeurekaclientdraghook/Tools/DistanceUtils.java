package com.luo.springcloudeurekaclientdraghook.Tools;

import com.luo.springcloudeurekaclientdraghook.entity.Draghook;

import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.List;

public class DistanceUtils {

    private static final double EARTH_RADIUS = 6371393; // 平均半径,单位：m


    public static double getDistance(double a,double b,double c,double d) {

        // 自身
        Point2D pointA = new Point2D.Double(b, a);
        // 目标物
        Point2D pointB = new Point2D.Double(d, c);


        // 经纬度（角度）转弧度。弧度用作参数，以调用Math.cos和Math.sin
        double radiansAX = Math.toRadians(pointA.getX()); // A经弧度
        double radiansAY = Math.toRadians(pointA.getY()); // A纬弧度
        double radiansBX = Math.toRadians(pointB.getX()); // B经弧度
        double radiansBY = Math.toRadians(pointB.getY()); // B纬弧度


        double cos = Math.cos(radiansAY) * Math.cos(radiansBY) * Math.cos(radiansAX - radiansBX)
                + Math.sin(radiansAY) * Math.sin(radiansBY);
        double acos = Math.acos(cos); // 反余弦值

        return EARTH_RADIUS * acos; // 最终结果
    }


    public static List<Draghook> draghooksDistance(List<Draghook>list,double latitude,double longitude)
    {
        DecimalFormat df = new DecimalFormat( "0.0");
        DecimalFormat df1 = new DecimalFormat( "0");
        double dis=0;
        if(list!=null)
        {
            for(Draghook draghook:list)
            {
                dis=DistanceUtils.getDistance(latitude,longitude,draghook.getLatitude(),draghook.getLongitude());
                if(dis/1000 <100&& dis>1000) {
                    draghook.setDistance(df.format(dis / 1000) + "km");
                }else if(dis<1000)
                {
                    draghook.setDistance(df1.format(dis ) + "m");

                }
                else
                {
                    draghook.setDistance("在远方");
                }

            }
        }

        return list;
    }


    public static Draghook draghookDistance(Draghook draghook,double latitude,double longitude)
    {
        DecimalFormat df = new DecimalFormat( "0.0");
        double dis=0;


        dis=DistanceUtils.getDistance(latitude,longitude,draghook.getLatitude(),draghook.getLongitude());
        if(dis/1000 <100) {
            draghook.setDistance(df.format(dis / 1000) + "km");
        }else
        {
            draghook.setDistance("在远方");
        }
        return  draghook;
    }

}
