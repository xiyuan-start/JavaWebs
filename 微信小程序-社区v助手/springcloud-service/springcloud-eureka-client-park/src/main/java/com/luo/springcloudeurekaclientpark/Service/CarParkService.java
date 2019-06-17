package com.luo.springcloudeurekaclientpark.Service;

import com.luo.springcloudeurekaclientpark.Dao.CarParkDao;
import com.luo.springcloudeurekaclientpark.Dao.OrderDao;
import com.luo.springcloudeurekaclientpark.Tools.DistanceUtils;
import com.luo.springcloudeurekaclientpark.entity.CarOrder;
import com.luo.springcloudeurekaclientpark.entity.CarPark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarParkService {

    @Autowired
    CarParkDao carParkDao;

    @Autowired
    OrderDao orderDao;

    //获取所有停车场
    public List<CarPark>GetAllCarPark(double latitude,double longitude)
    {

        List<CarPark> list =carParkDao.getAllBy( latitude,  longitude);

        return DistanceUtils.CarParksDistance(list,latitude,longitude);
    }

    //根据Id获取停车场详情
    public CarPark GetCarParkDetail(Integer id)
    {
        return carParkDao.getCarParkById(id);
    }


    //预约、停车
    public void parking(Integer id)
    {

        CarPark carPark =carParkDao.getCarParkById(id);
        carPark.parking();
        carParkDao.save(carPark);

    }

    //离开、取消
    public void leave(Integer id)
    {
        CarPark carPark =carParkDao.getCarParkById(id);
        carPark.leave();
        carParkDao.save(carPark);
    }

    //预约是查看是已预约
    public Boolean IsPreOrder(String platNumber)
    {
        List<CarOrder>list=orderDao.findAllByPlateNumberAndType(platNumber);

        if(list!=null&&list.size()>0)
        {
            return true;
        }

        return false;


    }

}
