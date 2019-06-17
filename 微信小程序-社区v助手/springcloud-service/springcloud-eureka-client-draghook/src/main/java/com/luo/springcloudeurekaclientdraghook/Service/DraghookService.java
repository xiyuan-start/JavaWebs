package com.luo.springcloudeurekaclientdraghook.Service;

import com.luo.springcloudeurekaclientdraghook.Dao.DraghookDao;
import com.luo.springcloudeurekaclientdraghook.Tools.DistanceUtils;
import com.luo.springcloudeurekaclientdraghook.entity.Draghook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;

@Service
public class DraghookService {

    @Autowired
    DraghookDao draghookDao;

    public void SaveNewDraghook(Draghook draghook)
    {
        draghookDao.save(draghook);
    }

    public List<Draghook>getDraghooksbyPage(Integer offset,Integer limit,double latitude,double longitude)
    {
        Pageable pageable=new PageRequest(offset/limit,limit);
        List<Draghook>list=draghookDao.getDraghooksByPage(pageable);
        return DistanceUtils.draghooksDistance(list,latitude,longitude);

    }

    public List<Draghook>getMyDraghooksbyPage(Integer PageNum,Integer PageSize,String openId)
    {

        //分页查询PageNum从0开始
        Pageable pageable=new PageRequest(PageNum-1,PageSize);
        return draghookDao.getDraghooksByOpenId(openId,pageable);

    }
    public Draghook GetDraghookbyIdAndOpenId(Integer id)
    {
        return draghookDao.getDraghooksByIdAnduserKey(id);
    }

    public List<Draghook> GetLastestDraghooks(String openId,double latitude,double longitude)
    {

        Pageable pageable=new PageRequest(0,10);


        List<Draghook>list= draghookDao.getLastDrahooks(openId,pageable);
        return DistanceUtils.draghooksDistance(list,latitude,longitude);
    }

    //根据id获取拉手任务
    public Draghook  getDraghooksById(Integer id)
    {
        return  draghookDao.getDraghooksById(id);
    }

    //通过id集合获取task
    public List<Draghook>getDraghooksByIds(Set<Integer>set)
    {
        return draghookDao.getDraghooksByIds(set);
    }

    //根据ID删除拉钩任务
    public void removebydynId(Integer id)
    {
        draghookDao.delete(id);
    }
}
