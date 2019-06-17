package com.luo.springcloudeurekaclientdraghook.Service;

import com.luo.springcloudeurekaclientdraghook.entity.Relme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;
import java.util.TreeSet;

@Service
public class RelmeService {

    @Resource(name = "ObjectredisTemplate")
    ValueOperations<String,Object> valueOperations;



    //被@评论后设置提醒
    public void SetRelme(String openId, Integer synId)
    {

        String id="relme"+openId;

        Relme relme=(Relme) valueOperations.get(id);

        if (relme!=null)//已生成提示
        {
             relme.setFlag(1);//标记成未读
             relme.addsyn(synId);

        }
        else
        {
             relme=new Relme();
             relme.setFlag(1);
             Set<Integer>set =new TreeSet<>();
             set.add(synId);
             relme.setSynidlist(set);
             relme.setId(id);


        }

        valueOperations.set(id,relme);

    }

    //查看是否需要提醒
    public Boolean lookRelme(String openId)
    {
        String id="relme"+openId;
        Relme relme =(Relme)valueOperations.get(id);

        if(relme==null)
        {

            return false;

        }
        else if(relme.getFlag()==0)
        {
            return false;
        }

        else if (relme.getFlag()==1)
        {

             return true;
        }

        return false;
    }


    //标记全已读
    public void readedRelme(String openId)
    {
        String id="relme"+openId;
        Relme relme =(Relme)valueOperations.get(id);

        if (relme!=null)
        {
            relme.setFlag(0);
            valueOperations.set(id,relme);
        }

    }

    //获取Set集合
    public Set<Integer> getDraghookSet(String openId)
    {
        String id="relme"+openId;
        Relme relme =(Relme)valueOperations.get(id);
        return relme.getSynidlist();
    }

}
