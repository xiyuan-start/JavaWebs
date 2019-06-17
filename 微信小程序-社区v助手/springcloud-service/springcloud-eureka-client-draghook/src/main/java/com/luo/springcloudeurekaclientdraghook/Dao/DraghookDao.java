package com.luo.springcloudeurekaclientdraghook.Dao;

import com.luo.springcloudeurekaclientdraghook.entity.Comment;
import com.luo.springcloudeurekaclientdraghook.entity.Draghook;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Component
public interface DraghookDao extends JpaRepository<Draghook,Integer> {

    @Query("from Draghook  d  order by d.createTime ")
    public List<Draghook> getDraghooksByPage( Pageable pageable);

    @Query("from Draghook  d where d.userKey=?1 order by d.createTime ")
    public List<Draghook> getDraghooksByOpenId( String openId,Pageable pageable);

    @Query("from Draghook  d where d.id=?1 ")
    public Draghook getDraghooksByIdAnduserKey(Integer id);

    @Query("from Draghook d where  d.userKey =?1 order by d.createTime  ")
    public List<Draghook>getLastDrahooks(String openId,Pageable pageable);

    @Query("from Draghook  d where d.id=?1")
    public Draghook getDraghooksById(Integer id);

    @Query("from Draghook  d where d.id in ?1 order by d.createTime  ")
    public List<Draghook>getDraghooksByIds(Set<Integer> synSet);

}
