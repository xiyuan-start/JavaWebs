package com.luo.Test001;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;


import java.io.IOException;
import java.io.Reader;
import java.util.List;
public class UserTest {

    private static SqlSessionFactory sqlSessionFactory;
    //加载资源文件获取sqlSessionFactory
    @BeforeClass
    public static void init()
    {
        try {

            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();

        }catch (IOException io)
        {
            io.getStackTrace();
        }
    }
    @Test
    public void UserSelect()
    {
        SqlSession sqlSession=sqlSessionFactory.openSession();

        try {

            List<com.luo.Model.User>list=sqlSession.selectList("selectAll");

            for(com.luo.Model.User user:list)
            {

                System.out.println(user);
            }
        }
        finally {
            sqlSession.close();
        }


    }

}
