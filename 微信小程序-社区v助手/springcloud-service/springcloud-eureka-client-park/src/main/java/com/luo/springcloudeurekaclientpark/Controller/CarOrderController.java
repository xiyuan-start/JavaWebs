package com.luo.springcloudeurekaclientpark.Controller;


import com.luo.springcloudeurekaclientpark.Service.ActiveMQService;
import com.luo.springcloudeurekaclientpark.Service.CarParkService;
import com.luo.springcloudeurekaclientpark.Service.OrderService;
import com.luo.springcloudeurekaclientpark.Tools.WebTool.ResultMent;
import com.luo.springcloudeurekaclientpark.Tools.WebTool.SendJson;
import com.luo.springcloudeurekaclientpark.entity.CarOrder;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
public class CarOrderController {


    @Autowired
    OrderService orderService;

    @Autowired
    CarParkService carParkService;

    @Autowired
    ActiveMQService activeMQService;

    @PostMapping("/CreateNewCarOrder")
    public void CreateNewCarOrder(HttpServletRequest request, HttpServletResponse response,
                                  @RequestBody CarOrder carOrder



    ) throws IOException ,ParseException ,SchedulerException {



        ResultMent resultMent = new ResultMent();
        Integer points= orderService.GetUserPoints(carOrder.getUserId());
        if(points >=60) {
            if (carParkService.IsPreOrder(carOrder.getPlateNumber()))//已预约
            {
                resultMent.setStatus(-2);
            } else {//未预约
                carOrder.setType(1);
                activeMQService.sendMessageforNewOrder(carOrder);
                //减少车位数
                carParkService.parking(carOrder.getParkId());
                resultMent.setStatus(-1);
            }
        }else
        {
            resultMent.setStatus(points);
        }

        SendJson.sendJsonObject(request, response, resultMent);
    }




    @PostMapping("/MyOrders")
    public void MyOrders(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam(required = true) String openId



    ) throws IOException  {


        List<CarOrder> list=orderService.GetMyOrders(openId);

        Integer points=orderService.GetUserPoints(openId);

        //获取用户的信誉分


        ResultMent resultMent = new ResultMent();
        resultMent.setData(list);
        resultMent.setStatus(orderService.GetUserPoints(openId));

        SendJson.sendJsonObject(request, response, resultMent);
    }


    @PostMapping("/AlterOrders")
    public void MyOrders(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam(required = true) String id,
                         @RequestParam(required = true) Integer type,
                         @RequestParam(required = true)Integer park,
                         @RequestParam(required = true)Integer ord


    ) throws IOException {


        ResultMent resultMent = new ResultMent();
        if (type==5)
        {

            orderService.RemoveOrderById(id);
            if(ord!=3&&ord!=4) {
                carParkService.leave(park);
            }
        }
         else {
             CarOrder carOrder = orderService.GetOrderById(id);
             carOrder.setType(type);
             //在这里同步车库的车位数量
            if(type==3)
            {
             carParkService.leave(park);
            }

            orderService.SaveOrder(carOrder);

        }




        SendJson.sendJsonObject(request, response, resultMent);
    }
}
