package com.luo.springcloudeurekaclientnotice.Dao;

import com.luo.springcloudeurekaclientnotice.entity.Notice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public interface NoticeDao extends JpaRepository<Notice,Integer> {

    //根据id获取公告
    @Query(" from Notice e where e.id=?1")
    public Notice findById(Integer id);

    //分页查询
    @Query("from  Notice n order by n.id desc  ")
    public List<Notice>findAllBy(Pageable pageable);


}
