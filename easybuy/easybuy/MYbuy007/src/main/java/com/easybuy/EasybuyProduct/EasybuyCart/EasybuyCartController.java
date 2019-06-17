package com.easybuy.EasybuyProduct.EasybuyCart;


import com.easybuy.EasyBuyUser.EasybuyUserAddressService;
import com.easybuy.EasybuyOrder.EasybuyOrderService;
import com.easybuy.EasybuyProduct.EasybuyProductService;
import com.easybuy.entity.EasybuyOrder;
import com.easybuy.entity.EasybuyOrderDetail;
import com.easybuy.entity.EasybuyUser;
import com.easybuy.entity.EasybuyUserAddress;
import com.easybuy.other.ResultMent;
import com.easybuy.transform.StringUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;


@Controller
public class EasybuyCartController {


    @Resource(name = "ObjectredisTemplate")
    ValueOperations<String,Object> valueOperations;
    @Autowired
    EasybuyProductService easybuyProductService;
    @Autowired
    EasybuyUserAddressService userAddressService;
    @Autowired
    EasybuyOrderService easybuyOrderService;
    //进行购物车操作
    @RequestMapping("/Cart")
    public  void  CartAction(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam String action,
                               @RequestParam(required = false) Integer quantity,//商品的数量
                               @RequestParam(required = false) Long entityId ,  //商品的id
                               @RequestParam(required = false)String newAddress,//新地址
                               @RequestParam(required = false)String newRemark,   //地址备注
                               @RequestParam(required = false)Long addressId

    )throws  Exception
    {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        ResultMent resultMent =new ResultMent();
        EasybuyUser easybuyUser =(EasybuyUser) request.getSession().getAttribute("loginUser");

        if(easybuyUser!=null) {

            if ("add".equals(action))//加入购物车
            {

                //获取session中购物车属性
                //Shopping 要有属性帮助进行错误提醒
                ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
                ShoppingCartItem shoppingCartItem = new ShoppingCartItem(easybuyProductService.getProduceByid(entityId), quantity);
                if (cart != null) {

                    if (cart.add(shoppingCartItem)) {
                        //加入成功
                        resultMent.setStatus(1);

                    } else //加入失败
                    {
                        resultMent.setStatus(2);
                        resultMent.setMessage("加入购物车失败，请重试");

                    }

                } else//新建购物车
                {
                    resultMent.setStatus(1);
                    ShoppingCart shoppingCart = new ShoppingCart();

                    shoppingCart.add(shoppingCartItem);
                    request.getSession().setAttribute("cart", shoppingCart);
                }


            }//刷新购物车
            else if ("refreshCart".equals(action)) {

                ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
                //上传更新redis
                valueOperations.set(easybuyUser.getLoginName(),cart);

            }else if ("toSettlement".equals(action))     //进入结算界面

            {

                request.getRequestDispatcher("pre/settlement/toSettlement.jsp").forward(request,response);


            }else if("settlement1".equals(action))//结算第一步
            {

                request.getRequestDispatcher("pre/settlement/settlement1.jsp").forward(request,response);
                return;

            }else if("modCart".equals(action))//商品数量
            {
                //这里避免了数量被重置
                Integer quantity1=Integer.parseInt(request.getParameter("quantity")) ;
                //修改某个购物车元素的quantity
                ShoppingCart shoppingCart =(ShoppingCart) request.getSession().getAttribute("cart");
                if(quantity1==0)//删除商品
                {
                    shoppingCart.removeById(entityId);

                }
                else
                {

                    ShoppingCartItem shoppingCartItem=new ShoppingCartItem(easybuyProductService.getProduceByid(entityId),quantity1);
                    shoppingCart.modify(shoppingCartItem);
                }

               request.getSession().setAttribute("cart",shoppingCart);
                resultMent.setStatus(1);

            }


            else  if("settlement2".equals(action))//结算第二步
            {
                //根据用户id查询用户地址
                List<EasybuyUserAddress>addressList=userAddressService.getAllAddress(((EasybuyUser)((EasybuyUser) request.getSession().getAttribute("loginUser"))).getId());
               if(addressList!=null)
               {
                   request.setAttribute("userAddressList",addressList);
               }

                request.getRequestDispatcher("pre/settlement/settlement2.jsp").forward(request,response);
            }
            else if("settlement3".equals(action))//结算第二步
            {

                EasybuyUserAddress Useraddress;
                if(addressId==-1) {
                    //保存生产的用户地址
                     Useraddress = new EasybuyUserAddress();
                    Useraddress.setAddress(newAddress);
                    Useraddress.setCreateTime(new java.sql.Timestamp(new Date().getTime()));
                    Useraddress.setUserId(easybuyUser.getId());
                    Useraddress.setRemark(newRemark);
                    Useraddress.setIsDefault(1);
                    userAddressService.saveAdress(Useraddress);
                }
                else
                {

                    Useraddress=userAddressService.getOneAddressById(addressId);

                }

                //生产订单(订单和详细订单)
                ShoppingCart shoppingCart=(ShoppingCart) request.getSession().getAttribute("cart");
                EasybuyOrder easybuyOrder =new EasybuyOrder();
                easybuyOrder.setCost(shoppingCart.getSum());
                easybuyOrder.setUserAddress(Useraddress.getAddress());
                easybuyOrder.setCreateTime(new java.sql.Timestamp(new Date().getTime()));
                easybuyOrder.setLoginName(easybuyUser.getLoginName());
                easybuyOrder.setUserId(easybuyUser.getId());
                easybuyOrder.setSerialNumber(StringUtils.randomUUID()); //随机生成订单号
                easybuyOrderService.saveOrder(easybuyOrder);

                 //根据订单号获得orderid
                Long ordid=easybuyOrderService.getOrdid(easybuyOrder.getSerialNumber());


                //改变商品的库存量
                for(ShoppingCartItem cartItem :shoppingCart.getItems())
                {
                      easybuyProductService.setEasybuyProductStock(cartItem.getQuantity().longValue(),cartItem.getProduct().getId());

                      //生产详细订单
                    EasybuyOrderDetail easybuyOrderDetail =new EasybuyOrderDetail();
                    easybuyOrderDetail.setCost(cartItem.getQuantity()*cartItem.getProduct().getPrice());
                    easybuyOrderDetail.setOrderId(ordid);
                    easybuyOrderDetail.setProductId(cartItem.getProduct().getId());
                    easybuyOrderDetail.setQuantity(cartItem.getQuantity());

                    easybuyOrderService.saveOrderDetail(easybuyOrderDetail);
                }

                //将购物车中的商品给清除
                shoppingCart.remove();
                shoppingCart.setSum(0);
                valueOperations.set(easybuyUser.getLoginName(),shoppingCart);
                request.setAttribute("currentOrder",easybuyOrder);
                request.getRequestDispatcher("pre/settlement/settlement3.jsp").forward(request,response);
                return;
            }

        }else
        {
            resultMent.setStatus(3);

        }

        PrintWriter out =response.getWriter();
       JSONObject jsonObject =JSONObject.fromObject(resultMent);
        out.print(jsonObject);
        out.flush();
        out.close();


    }



}
