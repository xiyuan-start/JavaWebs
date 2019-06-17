package com.easybuy.EasybuyProduct.EasybuyCart;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//用这个类管理购物车
public class ShoppingCart {
//购物车子类（商品和它的数量）
private List<ShoppingCartItem>items=new ArrayList<>();


private double sum=0;

    //对集合中的元素做 增删减查  并且计算总价格杀

    //添加  是否有这一项了    该商品的数量是否为0


    public ShoppingCart()
    {

    }



    //加入购物
    public boolean add(ShoppingCartItem m)
    {
        if (m.getProduct().getStock()>m.getQuantity())//检查存货是否足够
        {
            for (ShoppingCartItem s : items) {

                if (s.getProduct().getId() == m.getProduct().getId()) //购物车已有该商品
                {
                     s.setQuantity(s.getQuantity()+m.getQuantity());
                    sum=sum+m.getQuantity()*m.getProduct().getPrice();
                     return  true;
                }
            }

            //购物车中没有这个商品
            items.add(m);
            sum=sum+m.getQuantity()*m.getProduct().getPrice();
            return true;
        }
        return false;

    }

    //修改子类的一个商品的数量
    public void modify(ShoppingCartItem m)
    { if (m.getProduct().getStock()>m.getQuantity())//检查存货是否足够
    {
        for (ShoppingCartItem s : items) {

            if (s.getProduct().getId() == m.getProduct().getId()) //购物车已有该商品
            {
                Integer q1= s.getQuantity();
                Integer q2=m.getQuantity();
                s.setQuantity(q2);
                if (q1>q2)
                {

                    sum=sum-(q1-q2)*m.getProduct().getPrice();
                }
                if (q1<q2)
                {
                    sum=sum+(q2-q1)*m.getProduct().getPrice();
                }

            }
        }



    }


    }

    //清空购物车
    public  void remove()
    {

        this.items.removeAll(items);
    }

    //根据id移除一个商品
    public void removeById(Long id)
    {
        Iterator<ShoppingCartItem> iterable=items.iterator();

        while (iterable.hasNext())
        {
            ShoppingCartItem item=iterable.next();
            if(item.getProduct().getId()==id) {
                iterable.remove();
                sum=sum-item.getQuantity()*item.getProduct().getPrice();
            }
        }


    }


    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
