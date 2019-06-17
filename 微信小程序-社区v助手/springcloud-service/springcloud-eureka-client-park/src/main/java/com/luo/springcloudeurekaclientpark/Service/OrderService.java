package com.luo.springcloudeurekaclientpark.Service;
import static org.quartz.JobBuilder.*;
import com.luo.springcloudeurekaclientpark.Dao.OrderDao;
import com.luo.springcloudeurekaclientpark.Tools.CharacterEncodingFilter;
import com.luo.springcloudeurekaclientpark.Tools.Quartz.ScheduledTasks;
import com.luo.springcloudeurekaclientpark.Tools.StringUtils;
import com.luo.springcloudeurekaclientpark.Tools.WebTool.DateUtils;
import com.luo.springcloudeurekaclientpark.entity.CarOrder;
import com.luo.springcloudeurekaclientpark.entity.CarPark;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.soap.Detail;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {


    @Autowired
    OrderDao orderDao;

    @Autowired
    CarParkService carParkService;

    @Autowired
    ScheduledTasks scheduledTasks;

    @Autowired
    RestTemplate restTemplate;


    //生成新的订单
    public void CreateNewOrder(CarOrder carOrder)  throws ParseException ,SchedulerException
    {
        //通过UUID生成唯一的订单号
        carOrder.setId(StringUtils.randomUUID());

        String endtime=carOrder.getEndDate().replace('-','/');
        Date date=new Date(endtime);
        orderDao.save(carOrder);
        remove(carOrder.getId(),carOrder.getParkId(),DateUtils.getCron(date));



    }

    //保存订单
    public void SaveOrder(CarOrder carOrder)
    {
        orderDao.save(carOrder);
    }



    //获取自己的订单
    public List<CarOrder>GetMyOrders(String userId)
    {

        return orderDao.findCarOrdersByUserIdtype(userId);

    }


    //根据订单号获取订单
    public CarOrder GetOrderById(String id)
    {
        return orderDao.GetOneById(id);
    }

    //根据订单号删除订单
    public void RemoveOrderById(String id)
    {


        orderDao.RemoveOneById(id);



    }

    //根据订单号使订单失效
    public void disableOrder(String ordId,Integer parkId)
    {

        CarOrder carOrder=orderDao.GetOneById(ordId);
        if(carOrder!=null) {

            if(carOrder.getType()==1)
            {
                carParkService.leave(parkId);
                carOrder.setType(4);
                orderDao.save(carOrder);
                reduce(carOrder.getUserId());
            }


        }


    }

    //通过定时器来删除
    public void remove(String ordID,Integer parkId,String time) throws ParseException,SchedulerException
    {

        //定义一个JobDetail
        JobDetail job = newJob(ScheduledTasks.class) //定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                .withIdentity(ordID, "group1") //定义name/group
                .usingJobData("name", ordID) //定义属性
                .usingJobData("parkId",parkId)
                .build();

        //作业的触发器
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .startNow()
                .withIdentity(ordID, "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule(time)). //在任务调度器中，使用任务调度器的 CronScheduleBuilder 来生成一个具体的 CronTrigger 对象
                build();

        //得到默认的调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
         scheduler.scheduleJob(job,cronTrigger);
         scheduler.start();

    }

    //预约过期 减少其信誉分
    public void reduce(String id)
    {

        this.restTemplate.getForObject("http://139.196.137.129:8000/Reduce/"+id,String.class);
    }

    //获取用户信誉值
    public  Integer GetUserPoints(String id)
    {

         return new Integer(this.restTemplate.getForObject("http://139.196.137.129:8000/GetUser/"+id,String.class));
    }

}
