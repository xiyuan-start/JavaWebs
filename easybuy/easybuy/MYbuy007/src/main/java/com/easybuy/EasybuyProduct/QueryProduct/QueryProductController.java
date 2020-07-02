package com.easybuy.EasybuyProduct.QueryProduct;


import com.easybuy.other.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QueryProductController {

    @Autowired
    ProductService service;

    //跳转到查询界面
    @RequestMapping("/Queryproduct")
    public String list(@RequestParam(required = false) String queryString, String catalog_name, String price,
                       String sort, @RequestParam(required = false,defaultValue = "1") Integer currentPage, Model model) throws Exception {

        System.out.println("查询"+queryString);
        Pager pager =new Pager();
        pager.setUrl("Queryproduct?queryString="+queryString);
        pager.setCurrentPage(currentPage);

        ResultModel rm = service.getProducts(queryString, catalog_name, price,
                sort, currentPage);
        pager.setPageCount(rm.getPageCount());
        model.addAttribute("pager",pager);
        model.addAttribute("productList",rm.getProductList());
        model.addAttribute("total",rm.getProductList().size());
        System.out.println(rm.getProductList().size());
        return "pre/product/queryProductList";
    }





}