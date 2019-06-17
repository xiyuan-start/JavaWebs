package com.easybuy.other;
/*
 *该类的功能以及特点描述：电影订票系统的普通工具类 检查字符串类容是否有违法字符
 *该类是否被编译测试：否
 *@see(与之相关联的类)：   来源：
 *                     中间：
 *                     去处：
 *开发公司或单位：成007个人
 *版权：成007

 *@author(作者)：成007
 *@since（该文件使用的jdk）：JDK1.8
 *@version（版本）：1.0
 *@数据库查询方式：mysql+hibernate
 *@date(开发日期)：2018/6/20
 *改进：
 *最后更改日期：
 */
public class Isillegal {

    public static Isillegal isillegal = new Isillegal();

    private Isillegal() {

    }

    public static Isillegal getIsillegal() {
        return isillegal;
    }

    public boolean doIsillegal(String input) {

        if (input != null) {
            for (int i = input.length(); --i >= 0; ) {
                if (!Character.isLetterOrDigit(input.charAt(i))) {
                    return false;  //具有违法字符
                }
            }

        }

        return true;


    }

}