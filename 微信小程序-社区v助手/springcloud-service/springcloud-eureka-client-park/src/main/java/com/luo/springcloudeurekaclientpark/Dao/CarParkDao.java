package com.luo.springcloudeurekaclientpark.Dao;

import com.luo.springcloudeurekaclientpark.entity.CarPark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CarParkDao extends JpaRepository<CarPark,Integer> {


    @Query( nativeQuery=true, value ="select *, 6371393 * ACOS(COS(RADIANS(?1)) * COS(RADIANS(c.latitude)) * COS(RADIANS(?2 - c.longitude))+ SIN(RADIANS(?1)) * SIN(RADIANS(c.latitude))) as dis from car_park  as c order by dis limit 10")
    public List<CarPark>getAllBy(double latitude, double longitude);

    @Query("from CarPark  c where c.id =?1")
    public CarPark getCarParkById(Integer id);



}
