package com.easybuy.EasybuyProduct.QueryProduct;

import com.easybuy.entity.EasybuyProduct;

import java.io.Serializable;
import java.util.List;

public class ResultModel implements Serializable {

    private List<EasybuyProduct> productList;//商品集合
    private Long total;//商品总数

    // 总页数
    private int pageCount;
    // 当前页
    private int curPage;


    public ResultModel()
    {


    }

    public List<EasybuyProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<EasybuyProduct> productList) {
        this.productList = productList;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }
}
