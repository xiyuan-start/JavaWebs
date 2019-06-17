package com.easybuy.EasybuyProduct;

import com.easybuy.entity.EasybuyNews;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EasybuyNewsDao extends JpaRepository<EasybuyNews,Long> {


    //获取分页获取数据
    @Query("from EasybuyNews e ")
    List<EasybuyNews> listnews(Pageable pageable);

    //获取消息总数
    @Query("select count(e.id)from EasybuyNews e")
    Integer getsize();

    //根据id来获取商品
    @Override
    EasybuyNews getOne(Long id);
}
