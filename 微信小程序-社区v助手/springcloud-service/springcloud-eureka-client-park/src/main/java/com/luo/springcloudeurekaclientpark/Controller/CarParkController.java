package com.luo.springcloudeurekaclientpark.Controller;

import com.luo.springcloudeurekaclientpark.Service.CarParkService;
import com.luo.springcloudeurekaclientpark.Tools.WebTool.ResultMent;
import com.luo.springcloudeurekaclientpark.Tools.WebTool.SendJson;
import com.luo.springcloudeurekaclientpark.entity.CarPark;
import com.luo.springcloudeurekaclientpark.entity.Mark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CarParkController {

@Autowired
    CarParkService carParkService;

    @GetMapping("/GetParks")
    public void GetParks(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam(required = true) double   latitude,
                         @RequestParam(required = true) double  longitude



    ) throws IOException {


        List<CarPark>list=carParkService.GetAllCarPark(latitude,longitude);
        ResultMent resultMent = new ResultMent();
        List<Mark>marks=new ArrayList<>();

        if(list!=null) {
            for (CarPark carPark : list) {
                Mark mark = new Mark(carPark.getId(), carPark.getLatitude(), carPark.getLongitude());

                marks.add(mark);
            }
        }

        resultMent.setData(list);
        resultMent.setData2(marks);
        resultMent.setStatus(1);
        SendJson.sendJsonObject(request, response, resultMent);
    }


    @GetMapping("/GetPark")
    public void GetPark(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam(required = true)Integer id



    ) throws IOException {


         CarPark carPark=carParkService.GetCarParkDetail(id);

        ResultMent resultMent = new ResultMent();
        resultMent.setData(carPark);
        resultMent.setStatus(1);
        SendJson.sendJsonObject(request, response, resultMent);
    }

}
