package com.easybuy.EasybuyAdmin;


import com.easybuy.EasyBuyUser.EasyBuyUserService;
import com.easybuy.EasybuyOrder.EasybuyOrderService;
import com.easybuy.EasybuyProduct.EasybuyProductService;
import com.easybuy.entity.*;
import com.easybuy.other.Pager;
import com.easybuy.other.ResultMent;
import com.easybuy.transform.SecurityUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class EasybuyAdminController {

@Autowired
AdminService adminService;
@Autowired
EasybuyOrderService easybuyOrderService;
@Autowired
EasybuyProductService easybuyProductService;
@Autowired
EasyBuyUserService easyBuyUserService;
@RequestMapping("/Admin/news")
public String  adminnews(HttpServletRequest request, @RequestParam String action,@RequestParam(required = false) Integer currentPage,@RequestParam(required = false)Long id)throws IOException
{


    request.setCharacterEncoding("utf-8");
    if("queryNewsList".equals(action))
    {
      //进行分页查询一页十个新闻

        Pager pager=new Pager();
        pager. setPageCount(adminService.getnewssize()/10+1);
        pager.setUrl("Admin/news?action=queryNewsList");
        if(currentPage==null) {
            currentPage=1;
            pager.setCurrentPage(currentPage);
        }
        List<EasybuyNews>list=adminService.list10news(currentPage);
        pager.setRowCount(list.size());

        request.setAttribute("pager",pager);

        request.setAttribute("newsList",list);
        return "backend/news/newsList";
    }
    else if("newsDeatil".equals(action))
    {


            EasybuyNews news = adminService.getonenews(id);
            request.setAttribute("news", news);

        return "backend/news/newsDetail";

    }

return "1";

}

@RequestMapping("/Admin/user")
    public String AdminUser(HttpServletRequest request,
                            HttpServletResponse response,
                            @RequestParam String action,
                            @RequestParam(required = false) Integer currentPage,
                            @RequestParam(required = false)Long id
    )throws IOException
{
    request.setCharacterEncoding("utf-8");
    if ("index".equals(action))

    {
        return "backend/user/userInfo";
    }else if("queryUserList".equals(action))//查询所有用户
    {

        Pager pager =new Pager();
        pager.setUrl("Admin/user?action="+action);

        if(currentPage==null)
        {
            currentPage=1;
        }

        pager.setPageCount(adminService.getUserNum());
         List<EasybuyUser>list=adminService.getUser(currentPage);
        pager.setRowCount(list.size());
        request.setAttribute("pager",pager);

        request.setAttribute("userList",list);
        return "backend/user/userList";
    }else if ("toAddUser".equals(action))//添加用户
    {

        return "backend/user/toUpdateUser";   //根据是否传userid 判断是添加修改

    }else if("toUpdateUser".equals(action))

    {

        if(id!=null) {
            EasybuyUser user = adminService.getUserbyId(id);
            request.setAttribute("user", user);
        }
        return "backend/user/toUpdateUser";
    }else if("deleteUserById".equals(action))//根据id删除id
    {

        adminService.removeUserById(id);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",1);
        PrintWriter out =response.getWriter();
        out.print(jsonObject);
        out.flush();
        out.close();

    }else if("updateUser".equals(action))//更新或者添加用户
    {

        //必填项
        String loginName = request.getParameter("loginName");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");

        //非必填
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String identityCode = request.getParameter("identityCode");
        String type =request.getParameter("type");

        EasybuyUser user = new EasybuyUser();
        user.setLoginName(loginName);
        user.setUserName(userName);
        user.setPassword(SecurityUtils.md5Hex3(password));
        user.setSex(Long.valueOf(sex));

        if (type==null)
        {
            user.setType(0);
        }else
        {
            user.setType(Integer.parseInt(type));
        }
        if (email == null) {
            user.setEmail("");
        } else {
            user.setEmail(email);
        }
        if (mobile == null) {
            user.setMobile("");
        } else {
            user.setMobile(mobile);
        }

        if (identityCode == null) {
            user.setIdentityCode("");
        } else {
            user.setIdentityCode(identityCode);
        }


        //根据userLogin 判断用户是否存在
        EasybuyUser user1=adminService.IsexitUser(loginName);
        if(user1==null)//新建
        {
         easyBuyUserService.Register(user);

        }
        else //更新
        {

            user.setId(user1.getId());
         adminService.UpdateUser(user);

        }

        JSONObject jsonObject1=new JSONObject();
        jsonObject1.put("status",1);
        PrintWriter out1 =response.getWriter();
        out1.print(jsonObject1);
        out1.flush();
        out1.close();

    }




    return "1";

}

    @RequestMapping("/Admin/order")
    public String AdminOrder(HttpServletRequest request,@RequestParam String action,@RequestParam(required = false)Long userId,
                             @RequestParam(required = false, defaultValue = "1") Integer currentPage,
                             @RequestParam(required = false) Long orderId


                             )throws IOException
{
    request.setCharacterEncoding("utf-8");

    if("index".equals(action))
    {

     Pager pager =new Pager();

        List<EasybuyOrder> list;
     if(userId==null)
     {
         pager.setUrl("Admin/order?action=" + action);
         pager.setPageCount(easybuyOrderService.getOrderNum()/10+1);
         list=easybuyOrderService.getAllProductOrder(currentPage);

     }
     else {
         pager.setUrl("Admin/order?action=" + action + "&userId=" + userId);
         pager.setPageCount(easybuyOrderService.getOrdNumByUserid(userId) / 10 + 1);
          list = easybuyOrderService.getOrdbyuseriD(userId, currentPage);

     }

     //实例化订单下的详细订单
        for (EasybuyOrder order:list)
        {
            List<EasybuyOrderDetail>orderDetails=easybuyOrderService.getOrdDeatailByOrderid(order.getId());
            for (EasybuyOrderDetail orderDetail:orderDetails)
            {
                orderDetail.setProduct(easybuyProductService.getProduceByid(orderDetail.getProductId()));
            }
          order.setOrderDetailList(orderDetails);
        }


     //实例化详细订单下的商品
     pager.setRowCount(list.size());
     request.setAttribute("pager",pager);
     request.setAttribute("orderList",list);
     return "backend/order/orderList";
    } else if("queryOrderDeatil".equals(action))   //查询详细订单
    {

        List<EasybuyOrderDetail>orderDetails=easybuyOrderService.getOrdDeatailByOrderid(orderId);
        for (EasybuyOrderDetail orderDetail:orderDetails)
        {

            orderDetail.setProduct(easybuyProductService.getProduceByid(orderDetail.getProductId()));
        }

        request.setAttribute("orderDetailList",orderDetails);
        return "backend/order/orderDetailList";
    }else if("queryAllOrder".equals(action))
    {
        Pager pager =new Pager();

        List<EasybuyOrder> list=  list=easybuyOrderService.getAllProductOrder(currentPage);

        pager.setUrl("Admin/order?action=" + action);
        pager.setPageCount(easybuyOrderService.getOrderNum()/10+1);


        //实例化订单下的详细订单
        for (EasybuyOrder order:list)
        {
            List<EasybuyOrderDetail>orderDetails=easybuyOrderService.getOrdDeatailByOrderid(order.getId());
            for (EasybuyOrderDetail orderDetail:orderDetails)
            {
                orderDetail.setProduct(easybuyProductService.getProduceByid(orderDetail.getProductId()));
            }
            order.setOrderDetailList(orderDetails);
        }


        //实例化详细订单下的商品
        pager.setRowCount(list.size());
        request.setAttribute("pager",pager);
        request.setAttribute("orderList",list);
        return "backend/order/orderList";

    }

    return "1";
}

   //商品管理
    @RequestMapping("/Admin/product")
    public  String AdminProduct(HttpServletRequest request,HttpServletResponse response,
                                @RequestParam String action,
                                @RequestParam(required = false) Integer currentPage,
                                @RequestParam(required = false) Long id,
                                @RequestParam(required = false)String photo,
                                @RequestBody(required = false) EasybuyProduct Product
                                )throws IOException
    {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        JSONObject jsonObject=new JSONObject();
        if ("index".equals(action))//进入商品列表
        {

            Pager pager=new Pager();
            pager.setUrl("Admin/product?action="+action);
            pager.setPageCount(adminService.getProductNum()/10+1);

            if(currentPage==null)
            {
                currentPage=1;
            }

            List<EasybuyProduct>list =adminService.getProductList(currentPage);
            pager.setRowCount(list.size());
            request.setAttribute("pager",pager);
            request.setAttribute("productList",list);
            return "backend/product/productList";
        }else if("toAddProduct".equals(action))//跳转到商品管理
        {
            List<EasybuyProductCategory>list=easybuyProductService.getProductCateGoryByParentId(Long.parseLong("0"));
            request.setAttribute("productCategoryList1",list);
            return "backend/product/toAddProduct";

        }else if("toUpdateProduct".equals(action))
        {

            //根据id 查询商品

            if (id!=null) {
                EasybuyProduct easybuyProduct = easybuyProductService.getProduceByid(id);
                request.setAttribute("product",easybuyProduct);
            }
            List<EasybuyProductCategory>list=easybuyProductService.getProductCateGoryByParentId(Long.parseLong("0"));
            request.setAttribute("productCategoryList1",list);

            return "backend/product/toAddProduct";
        }

        else if("deleteById".equals(action)) //根据id删除商品
        {
            adminService.removeUserById(id);
            jsonObject.put("status",1);

        }else if ("addProduct".equals(action)) //修改或者添加商品
        {
             Product.setFileName(photo);
             Product.setCatalog_name("");
            //根据id 获得商品
            EasybuyProduct product=easybuyProductService.getProduceByid(id);
            if (product==null)//添加商品
            {
             easybuyProductService.addproduct(Product);

            }
            else//修改商品
            {
                easybuyProductService.updateProductById(product);

            }

                return "backend/product/productList";

        }



        PrintWriter out =response.getWriter();
        out.print(jsonObject);
        out.flush();
        out.close();


        return "1";
    }



    //商品分类管理
    @RequestMapping("/Admin/productCategory")
    public String AdminProductCategory(HttpServletRequest request,HttpServletResponse response,
                                       @RequestParam String action,
                                       @RequestParam(required = false)Integer currentPage,
                                       @RequestParam(required = false)Long id,
                                       @RequestParam(required = false)Long type,
                                       @RequestParam(required = false)Long productCategoryLevel1,
                                       @RequestParam(required = false)Long productCategoryLevel2,
                                       @RequestParam(required = false)String name,
                                       @RequestParam(required = false ,defaultValue = "0")Long parentId
                                       )throws IOException
    {
        JSONObject jsonObject=new JSONObject();
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        if("index".equals(action))
        {
            Pager pager =new Pager();
            pager.setUrl("Admin/productCategory?action="+action);
            if(currentPage==null)
            {
                currentPage=1;
            }

            pager.setPageCount(easybuyProductService.getProductCateGoryNum()/10+1);
            List<EasybuyProductCategory>list=easybuyProductService.getAllProductCategory(currentPage);
            pager.setRowCount(list.size());
            request.setAttribute("productCategoryList",list);
            request.setAttribute("pager",pager);
            return "backend/productCategory/productCategoryList";

        }
        else if("deleteProductCategory".equals(action))  //根据id删除商品分类

        {

            easybuyProductService.delete(type,id);

            jsonObject.put("status",1);

        }else if("addProductCategory".equals(action)) //添加商品分类
        {

            EasybuyProductCategory easybuyProductCategory=new EasybuyProductCategory();
            easybuyProductCategory.setIconClass("");
            easybuyProductCategory.setName(name);
            easybuyProductCategory.setType(type);
            switch (type.intValue())
            {
                case 1:
                    easybuyProductCategory.setParentId(0);
                    break;
                case 2:
                    easybuyProductCategory.setParentId(productCategoryLevel1);
                    break;
                case 3:
                    easybuyProductCategory.setParentId(productCategoryLevel2);
                    break;

            }

         easybuyProductService.saveProductCategory(easybuyProductCategory);
            jsonObject.put("status",1);
        }else if("toAddProductCategory".equals(action)) //跳转到添加界面
        {
            List<EasybuyProductCategory>list=easybuyProductService.getProductCateGoryByParentId(parentId);
            request.setAttribute("productCategoryList1",list);
            return "backend/productCategory/toAddProductCategory";
        }else  if("toUpdateProductCategory".equals(action))
        {
            //根据id查询 分类
            List<EasybuyProductCategory>list=easybuyProductService.getProductCateGoryByParentId(parentId);
            request.setAttribute("productCategoryList1",list);
            EasybuyProductCategory easybuyProductCategory=easybuyProductService.getProductCategoryById(id);
            request.setAttribute("productCategory",easybuyProductCategory);
            return "backend/productCategory/toAddProductCategory";
        }else if("queryProductCategoryList".equals(action))//查询子分类
        {
            //根据parsentId查询子节点
            System.out.println("查询子节点");

            List<EasybuyProductCategory>list=easybuyProductService.getProductCateGoryByParentId(parentId);



            ResultMent resultMent =new ResultMent();
            resultMent.setStatus(1);
            resultMent.setData(list);
            PrintWriter out =response.getWriter();
            out.print(JSONObject.fromObject(resultMent));
            out.flush();
            out.close();


        }else if("modifyProductCategory".equals(action)) //修改分类
        {
            EasybuyProductCategory easybuyProductCategory=new EasybuyProductCategory();
            easybuyProductCategory.setIconClass("");
            easybuyProductCategory.setName(name);
            easybuyProductCategory.setType(type);
            switch (type.intValue())
            {
                case 1:
                    easybuyProductCategory.setParentId(0);
                    break;
                case 2:
                    easybuyProductCategory.setParentId(productCategoryLevel1);
                    break;
                case 3:
                    easybuyProductCategory.setParentId(productCategoryLevel2);
                    break;
            }
            easybuyProductCategory.setId(id);

            easybuyProductService.UpdateProductCategoryById(easybuyProductCategory);
            jsonObject.put("status",1);

        }


        PrintWriter out =response.getWriter();
        out.print(jsonObject);
        out.flush();
        out.close();

        return "1";
    }
}
