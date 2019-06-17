package com.luo.springcloudeurekaclientpark.Tools.WebTool;




import com.luo.springcloudeurekaclientpark.Tools.EmptyUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class WebService {


    //由于页面的跳转应该发生在Controller 而不是业务逻辑层service层
    //所以要封装 页面跳转行为
    //对json的发送行为也进行封装
    //结合重定向
    //利用data作为URL的存放对象
    public static void toview(HttpServletRequest request, HttpServletResponse response, ResultMent resultMent)throws Exception
    {
        Object result=resultMent.getData();
        if (!EmptyUtils.isEmpty(result)) {
            if (result instanceof String) {
                String viewName = result.toString() + ".jsp";
                request.getRequestDispatcher(viewName).forward(request, response);
            } else {
                //封装JSON的发送
               SendJson.sendJsonObject(request,response,resultMent);
            }
        }

    }





}
