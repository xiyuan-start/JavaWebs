package com.luo.springcloudeurekaclientuser.Dao;

import com.luo.springcloudeurekaclientuser.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface ConsumerDao extends JpaRepository<Consumer,String> {


    @Query("from Consumer c where c.openId = ?1")
    public Consumer getByOpenId(String openid);
}
