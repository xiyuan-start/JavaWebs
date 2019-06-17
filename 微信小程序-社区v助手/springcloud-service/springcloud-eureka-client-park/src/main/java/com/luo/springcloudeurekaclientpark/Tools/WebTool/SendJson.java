package com.luo.springcloudeurekaclientpark.Tools.WebTool;


import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SendJson {


    //将json的发送做封装处理
    public static void sendJsonObject(HttpServletRequest request, HttpServletResponse response, ResultMent resultMent)throws IOException
    {

        PrintWriter out=null;
        try {
            out = response.getWriter();
            JSONObject jsonObject=JSONObject.fromObject(resultMent);
            out.print(jsonObject);
            out.flush();
        } finally {
            out.close();
        }


    }

}
