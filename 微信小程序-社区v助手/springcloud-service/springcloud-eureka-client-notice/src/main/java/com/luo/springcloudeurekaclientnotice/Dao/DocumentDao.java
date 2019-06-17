package com.luo.springcloudeurekaclientnotice.Dao;

import com.luo.springcloudeurekaclientnotice.entity.Document;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DocumentDao extends JpaRepository<Document,Integer> {



    //分页获取
    @Query("from  Document  e order by e.count desc ")
    public List<Document> GetAllByPage(Pageable pageable);


    //根据Id获取
    @Query("from Document e where e.id=?1")
    public Document GetOneById(Integer id);

    //关键字模糊查询
    @Query("from Document e where e.title LIKE CONCAT('%',?1,'%')")
    public List<Document>GetMoreBytitle(String title);






}
