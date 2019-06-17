package com.easybuy.EasybuyOrder;

import com.easybuy.entity.EasybuyOrder;
import com.easybuy.entity.EasybuyOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EasybuyOrderService {

    @Autowired
    EasybuyOrderDao easybuyOrderDao;

    @Autowired
    EasybuyOrderDetailDao easybuyOrderDetailDao;

    //保存订单
    public  void  saveOrder(EasybuyOrder order)
    {
        easybuyOrderDao.save(order);
    }

    //保存详细订单
    public void saveOrderDetail(EasybuyOrderDetail easybuyOrderDetail)
    {
        easybuyOrderDetailDao.saveAndFlush(easybuyOrderDetail);
    }

    //根据订单号获得订单id
    public Long getOrdid(String s)
    {
        return easybuyOrderDao.getOrderid(s);
    }

    //根据用户id获取订单
     public List<EasybuyOrder> getOrdbyuseriD(Long id, Integer currentpage)
     {

         Pageable pageable= new PageRequest(currentpage-1,10);
         return easybuyOrderDao.getAllByUserId(id,pageable);
     }

     //根据用户id获得订单的数量
    public Integer getOrdNumByUserid(Long id)
    {
        return easybuyOrderDao.getorderNumByUserid(id);
    }

    //根据订单号获取详细订单
    public List<EasybuyOrderDetail>getOrdDeatailByOrderid(Long orderid)
    {
        return easybuyOrderDetailDao.getAllByOrderId(orderid);
    }

    //获取Order
    public List<EasybuyOrder>getAllProductOrder(Integer currentPage)
    {
        Pageable pageable=new PageRequest(currentPage-1,10);
        return easybuyOrderDao.getAll(pageable);

    }
    //获取Order总数
    public Integer getOrderNum()
    {
        return easybuyOrderDao.getEasybuyOrderNum();
    }


}
