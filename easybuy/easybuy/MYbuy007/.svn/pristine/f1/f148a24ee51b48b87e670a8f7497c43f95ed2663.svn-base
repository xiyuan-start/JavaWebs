package com.easybuy.EasybuyAdmin;


import com.easybuy.EasyBuyUser.EasyBuyUserDao;
import com.easybuy.EasybuyProduct.EasybuyNewsDao;
import com.easybuy.EasybuyProduct.EasybuyProductDao;
import com.easybuy.entity.EasybuyNews;
import com.easybuy.entity.EasybuyProduct;
import com.easybuy.entity.EasybuyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminService {


    @Autowired
    EasybuyNewsDao dao;
    @Autowired
    EasyBuyUserDao userDao;
    @Autowired
    EasybuyProductDao easybuyProductDao;

    public List<EasybuyNews> list10news(Integer currentpage) {

        Pageable pageable = new PageRequest(currentpage - 1, 10);
        return dao.listnews(pageable);


    }

    public Integer getnewssize() {
        return dao.getsize();
    }

    //根据id获取新闻
    public EasybuyNews getonenews(Long id) {
        return dao.getOne(id);
    }

    //获取用户数量
    public Integer getUserNum() {
        return userDao.getUserNum();
    }

    //获取用户信息
    public List<EasybuyUser> getUser(Integer currentPage) {
        Pageable pageable = new PageRequest(currentPage - 1, 10);

        return userDao.getAllUser(pageable);
    }

    //根据用户id获取用户信息
    public EasybuyUser getUserbyId(Long id) {
        return userDao.lookuser(id);
    }

    //根据用户id删除用户
    public void removeUserById(Long id) {
        userDao.removeById(id);
    }

    //根据登录名查询用户是否存在
    public EasybuyUser IsexitUser(String loginName)

    {
        return userDao.findByLoginName(loginName);

    }


    //修改用户信息
    public void UpdateUser(EasybuyUser user) {


        userDao.UpdateUser(user.getLoginName(), user.getUserName(), user.getPassword(), user.getIdentityCode(), user.getEmail(), user.getMobile(), user.getType(), user.getId());

    }

    //获取商品的总数
    public Integer getProductNum() {
        return easybuyProductDao.getEasybuyProductNum();
    }

    //分页获取商品信息
    public List<EasybuyProduct> getProductList(Integer currentPage) {
        Pageable pageable = new PageRequest(currentPage - 1, 10);
        return easybuyProductDao.indexlist(pageable);

    }

    //根据商品id 删除商品
    public void deleteProductById(Long id)
    {
        removeUserById(id);
    }
}
