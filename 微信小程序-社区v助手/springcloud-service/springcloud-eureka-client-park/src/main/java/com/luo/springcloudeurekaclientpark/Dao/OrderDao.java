package com.luo.springcloudeurekaclientpark.Dao;

import com.luo.springcloudeurekaclientpark.entity.CarOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
@Component
@Transactional
public interface OrderDao extends JpaRepository<CarOrder,String> {

    @Query("from CarOrder o where o.userId =?1")
    public List<CarOrder> GetMoreByUserId(String userId);


    @Query("from CarOrder  o where o.id=?1")
    public CarOrder GetOneById(String id);

    @Query("from CarOrder o where o.plateNumber=?1 and o.type  in (1,2)" )

    public List<CarOrder>findAllByPlateNumberAndType(String plateNumber);

    @Query("from CarOrder o where o.userId =?1  order by o.type " )
    public List<CarOrder>findCarOrdersByUserIdtype(String openId);


    @Modifying
    @Query("delete from CarOrder o where o.id=?1")
    public void RemoveOneById(String id);


}
