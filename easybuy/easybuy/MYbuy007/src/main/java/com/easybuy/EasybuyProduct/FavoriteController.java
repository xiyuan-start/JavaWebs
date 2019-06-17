package com.easybuy.EasybuyProduct;

import com.easybuy.entity.EasybuyProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

//用来进行收藏商品的表示层
@Controller
public class FavoriteController {

    @Autowired
    EasybuyProductService dao;
    @RequestMapping("/Favorite")
    public void favoriteList(HttpServletRequest request, HttpServletResponse response,@RequestParam String action)throws  Exception
    {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        System.out.println(action );
        if("toFavoriteList".equals(action))
        {


            request.getRequestDispatcher("pre/product/favoriteList.jsp").forward(request,response);



        }
       else if("addFavorite".equals(action)) //默认点击进入就添加到了收藏
        {

               Long id =Long.parseLong(request.getParameter("id"));
                System.out.println("id为"+id);
                Set<EasybuyProduct>list =(Set<EasybuyProduct>) request.getSession().getAttribute("recentProducts");
                if (list!=null)//该记录存在
                {
                    list.add(dao.getProduceByid(id));
                }
                else
                {
                    //该记录不存在
                    Set<EasybuyProduct>list1=new HashSet<>();
                    list1.add(dao.getProduceByid(id));
                    request.getSession().setAttribute("recentProducts",list1);


                }



        }



    }

}
