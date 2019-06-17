package com.luo.springcloudeurekaclientpark.Tools.Quartz;

import com.luo.springcloudeurekaclientpark.Service.OrderService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks implements Job {

    @Resource(name = "orderService")
     OrderService orderService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException{


        JobDataMap jobDataMap = context.getMergedJobDataMap();
        String name = jobDataMap.getString("name");
        Integer parkId =jobDataMap.getIntValue("parkId");
         scheduledTasks.orderService.disableOrder(name,parkId);

    }


    public static ScheduledTasks scheduledTasks;

    public ScheduledTasks() {
    }

    @PostConstruct
    public void init() {
        scheduledTasks = this;
        scheduledTasks.orderService=this.orderService;
    }
}
