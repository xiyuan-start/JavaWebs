package com.easybuy.EasybuyProduct;

import com.easybuy.entity.EasybuyNews;
import com.easybuy.entity.EasybuyProduct;
import com.easybuy.entity.EasybuyProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class EasybuyProductService {

    @Autowired
    EasybuyNewsDao easybuyNewsDao;


    @Autowired
    EasybuyProductDao easybuyProductDao;
    @Autowired
    EasybuyProductCategoryDao easybuyProductCategoryDao;

    public List<EasybuyNews> listnews() {
        Pageable pageable=new PageRequest(1,5);
        return easybuyNewsDao.listnews(pageable);
    }


    //根据id 获取八个商品

    public List<EasybuyProduct> geteigth(Long id) {

      //  Pageable pageable = new PageRequest(page, 8);

        return easybuyProductDao.Getsome(id);


    }

    //主页图片加载
    public List<EasybuyProduct> indexlist(int page, int size)
    {

        Pageable pageable = new PageRequest(page, size);

        return easybuyProductDao.indexlist(pageable);
    }


    //获取商品类
    public List<EasybuyProductCategory> listCategory() {
        return easybuyProductCategoryDao.finflist();
    }


    //根据parentid获得三层分类
    public List<EasyBuyProductsVo> listbyparentid() {

        //查询一级分类的列表
        List<EasyBuyProductsVo> productCategory1VoList = new ArrayList<EasyBuyProductsVo>();
        //查询一级分类
        List<EasybuyProductCategory> productCategory1List = easybuyProductCategoryDao.listbyparentid(Integer.valueOf(0).longValue());
        System.out.println("一级分类的数量"+productCategory1List.size());
        //查询二级分类
        for (EasybuyProductCategory product1Category : productCategory1List) {
            //封装一级分类
            EasyBuyProductsVo productCategoryVo = new EasyBuyProductsVo();
            productCategoryVo.setProductCategory(product1Category);

            List<EasyBuyProductsVo> productCategoryVo1ChildList = new ArrayList<EasyBuyProductsVo>();
            //根据一级分类查询二级分类
            //一级分类的id为二级分类 partentid
            List<EasybuyProductCategory> productCategory2List = easybuyProductCategoryDao.listbyparentid(product1Category.getId());

            for (EasybuyProductCategory productCategory2 : productCategory2List) {
                EasyBuyProductsVo productCategoryVo2 = new EasyBuyProductsVo();
                productCategoryVo1ChildList.add(productCategoryVo2);
                productCategoryVo2.setProductCategory(productCategory2);
                List<EasyBuyProductsVo> productCategoryVo2ChildList = new ArrayList<EasyBuyProductsVo>();
                productCategoryVo2.setProductCategoryVoList(productCategoryVo2ChildList);
                //根据二级分类查询三级分类的列表
                List<EasybuyProductCategory> productCategory3List = easybuyProductCategoryDao.listbyparentid(productCategory2.getId());
                for (EasybuyProductCategory productCategory3 : productCategory3List) {
                    EasyBuyProductsVo productCategoryVo3 = new EasyBuyProductsVo();
                    productCategoryVo3.setProductCategory(productCategory3);
                    productCategoryVo2ChildList.add(productCategoryVo3);
                }
            }
            productCategoryVo.setProductCategoryVoList(productCategoryVo1ChildList);
            productCategory1VoList.add(productCategoryVo);
        }
        return productCategory1VoList;

    }


    //查询单个商品信息
    public EasybuyProduct getProduceByid(Long id)
    {

        return easybuyProductDao.getEasybuyProductById(id);
    }

    //根据一级分类查询商品集
    public List<EasybuyProduct>getProductsBy1id(Long id, Integer currentPage)
    {

        Pageable pageable =new PageRequest(currentPage-1,20);
        return easybuyProductDao.findEasybuyProductsByCategoryLevel1Id(id,pageable);
    }

    //根据二级分类查询商品集
    public List<EasybuyProduct>getProductsBy2id(Long id, Integer currentPage)
    {

        Pageable pageable =new PageRequest(currentPage-1,20);
        return easybuyProductDao.findEasybuyProductsByCategoryLevel2Id(id,pageable);
    }

    //根据三级分类查询商品集
    public List<EasybuyProduct>getProductsBy3id(Long id, Integer currentPage)
    {

        Pageable pageable =new PageRequest(currentPage-1,20);

        return easybuyProductDao.findEasybuyProductsByCategoryLevel3Id(id,pageable);
    }

    //获取一级分类下的数量
    public Integer getnumsby1id(Long id)
    {
        return easybuyProductDao.getNumEasybuyProductsByCategoryLevel1Id(id);
    }

    //获取二级分类下的数量
    public Integer getnumsby2id(Long id)
    {
        return easybuyProductDao.getNumEasybuyProductsByCategoryLevel2Id(id);
    }
    //获取一级分类下的数量
    public Integer getnumsby3id(Long id)
    {
        return easybuyProductDao.getNumEasybuyProductsByCategoryLevel3Id(id);
    }


    //卖出商品
    public void setEasybuyProductStock(Long quantity,Long id)
    {

        easybuyProductDao.setEasybuyProductStock(quantity,id);
    }

    //添加商品
    public void addproduct(EasybuyProduct product)
    {
        easybuyProductDao.save(product);
    }

    //修改商品
    public void updateProductById(EasybuyProduct product)
    {
        easybuyProductDao.UpdateProductById(product.getName(),product.getFileName(),product.getPrice(),product.getStock(),product.getDescription(),product.getCategoryLevel1Id(),product.getCategoryLevel2Id(),product.getCategoryLevel3Id(),product.getId());


    }


    //获取分类的数量
    public Integer getProductCateGoryNum()
    {
        return easybuyProductCategoryDao.getEasybuyProductCategoryNum();
    }

    //分页查询分类
    public List<EasybuyProductCategory>getAllProductCategory(Integer currentPage)

    {
        Pageable pageable=new PageRequest(currentPage-1,10);
        return easybuyProductCategoryDao.findAllBy(pageable);
    }

    //根据id删除分类
    public void delete(Long type,Long id)
    {
        easybuyProductCategoryDao.deleteByIdAndType(type,id);
    }

    //添加分类
    public void saveProductCategory(EasybuyProductCategory easybuyProductCategory)
    {
        easybuyProductCategoryDao.save(easybuyProductCategory);
    }

    //根据id获得分类
    public EasybuyProductCategory getProductCategoryById(Long id)
    {
        return easybuyProductCategoryDao.getOne(id);
    }

    //根据parentId获得分类
    public  List<EasybuyProductCategory> getProductCateGoryByParentId(Long id)
    {
        return easybuyProductCategoryDao.listbyparentid(id);
    }

    //根据id修改分类
    public void UpdateProductCategoryById(EasybuyProductCategory easybuyProductCategory)
    {

        easybuyProductCategoryDao.UpdateProductCategoryById(easybuyProductCategory.getName(),easybuyProductCategory.getParentId(),easybuyProductCategory.getType(),easybuyProductCategory.getIconClass(),easybuyProductCategory.getId());
    }

}