package com.luo.springcloudeurekaclientpark.Tools;



import com.luo.springcloudeurekaclientpark.entity.CarPark;

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


    public static List<CarPark > CarParksDistance(List<CarPark>list, double latitude, double longitude)
    {
        DecimalFormat df = new DecimalFormat( "0.0");
        DecimalFormat df1 = new DecimalFormat( "0");
        double dis=0;

        if(list!=null)
        {
            for(int i=list.size()-1;i>=0;i--)
            {
                dis=DistanceUtils.getDistance(latitude,longitude,list.get(i).getLatitude(),list.get(i).getLongitude());
                if(dis>1000&&dis <5000) {
                    list.get(i).setDistance(df.format(dis / 1000) + "km");
                }else  if(dis<1000)
                {
                    list.get(i).setDistance(df1.format(dis) + "m");
                }

                else
                {
                    list.remove(list.get(i));
                }

            }
        }

        return list;
    }



}
