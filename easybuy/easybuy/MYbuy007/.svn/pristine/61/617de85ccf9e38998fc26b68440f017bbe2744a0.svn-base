package com.easybuy.EasybuyOrder;

import com.easybuy.entity.EasybuyOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EasybuyOrderDao extends JpaRepository<EasybuyOrder,Long> {

    //添加订单

    @Modifying
    @Override
    <S extends EasybuyOrder> S save(S s);

    @Query ("select e.id from EasybuyOrder e  where  e.serialNumber=?1")
    Long getOrderid(String serial);

    //根据用户id获取订单列表
    @Query("from EasybuyOrder e where e.userId=?1")
    List<EasybuyOrder>getAllByUserId(Long id, Pageable pageable);

    //根据用户id获取订单的总数
    @Query("select count(e.id) from  EasybuyOrder  e where e.userId=?1")
    Integer getorderNumByUserid(Long id);

    //获取订单
    @Query("from EasybuyOrder order ")
    List<EasybuyOrder>getAll(Pageable pageable);

    //获取订单数
    @Query("select count (e.id) from EasybuyOrder e")
    Integer getEasybuyOrderNum();
}
