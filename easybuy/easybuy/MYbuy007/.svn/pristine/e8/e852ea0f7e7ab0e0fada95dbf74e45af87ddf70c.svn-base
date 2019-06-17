package com.easybuy.EasybuyProduct;

import com.easybuy.entity.EasybuyProduct;
import com.easybuy.entity.EasybuyProductCategory;

import java.io.Serializable;
import java.util.List;

//用这个类来管理 商品和商品类
//利用这个解决层级分类
public class EasyBuyProductsVo implements Serializable {

    private EasybuyProductCategory productCategory;
    private List<EasyBuyProductsVo> productCategoryVoList;
    private List<EasybuyProduct> productList;


    public EasybuyProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(EasybuyProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public List<EasyBuyProductsVo> getProductCategoryVoList() {
        return productCategoryVoList;
    }

    public void setProductCategoryVoList(List<EasyBuyProductsVo> productCategoryVoList) {
        this.productCategoryVoList = productCategoryVoList;
    }

    public List<EasybuyProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<EasybuyProduct> productList) {
        this.productList = productList;
    }
}
