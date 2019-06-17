package com.easybuy.EasybuyOrder;

import com.easybuy.entity.EasybuyOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EasybuyOrderDetailDao extends JpaRepository<EasybuyOrderDetail,Long> {

    //保存详细订单
    @Modifying
    @Override
    <S extends EasybuyOrderDetail> S save(S s);


    //根据订单id获取详细订单
    @Query("from EasybuyOrderDetail e where e.orderId=?1")
    List<EasybuyOrderDetail> getAllByOrderId(Long orderid);

    @Override
    <S extends EasybuyOrderDetail> S saveAndFlush(S s);
}
