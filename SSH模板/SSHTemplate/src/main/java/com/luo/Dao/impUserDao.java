package com.luo.Dao;

import com.luo.Model.UserEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@Component
public class impUserDao extends HibernateDaoSupport implements UserDao   {

    /*
    * 每一个Dao都依赖SessionFactory
    * 可以使用 xml 注册Dao Bean  ref 的方法注入
    *
    * */
    @Resource
    public void setSessionFactory0(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public List<UserEntity> getUser() {
        return (List<UserEntity>) getHibernateTemplate().find("from UserEntity ");
    }


    public UserEntity getUserbyId(Integer id)
    {
        Session session=this.getSessionFactory().openSession();

        String hql="from UserEntity u where u.id=:id ";

        Query query=session.createQuery(hql);
        query.setParameter("id",id);

        UserEntity userEntity=(UserEntity)query.uniqueResult();
       return userEntity;

    }
}
