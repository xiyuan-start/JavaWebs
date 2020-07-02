package com.easybuy.EasyBuyUser;

import com.easybuy.entity.EasybuyUserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EasybuyUserAddressDao extends JpaRepository<EasybuyUserAddress,Long> {


    //根据用户id 获取所有地址
    @Query("from EasybuyUserAddress e where e.userId=?1")
    public List<EasybuyUserAddress>getAllByUserId(Long userId);


    //添加地址

    @Override
    <S extends EasybuyUserAddress> S save(S s);


    //根据用户id获得地址

    @Override
    EasybuyUserAddress getOne(Long aLong);
}