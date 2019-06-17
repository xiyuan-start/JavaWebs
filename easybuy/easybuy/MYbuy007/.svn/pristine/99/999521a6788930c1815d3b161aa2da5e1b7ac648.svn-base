package com.easybuy.EasybuyProduct;

import com.easybuy.entity.EasybuyProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EasybuyProductDao extends JpaRepository<EasybuyProduct,Long> {

    //获取指定类型的八个商品
    @Query("from EasybuyProduct e where  e.categoryLevel1Id=?1")
    List<EasybuyProduct> Getsome(long id);


    //主页加载
    @Query("from  EasybuyProduct e")
    List<EasybuyProduct> indexlist(Pageable pageable);


    //根据id获取一个商品
    @Query("from EasybuyProduct e where  e.id=?1")
    public EasybuyProduct getEasybuyProductById(Long id);

    //根据一级分类加载
    @Query("from  EasybuyProduct  e where  e.categoryLevel1Id=?1")
    public List<EasybuyProduct> findEasybuyProductsByCategoryLevel1Id(Long id, Pageable pageable);


    //根据二级分类加载
    @Query("from  EasybuyProduct  e where  e.categoryLevel2Id=?1")
    public List<EasybuyProduct> findEasybuyProductsByCategoryLevel2Id(Long id, Pageable pageable);


    //根据三级分类加载
    @Query("from  EasybuyProduct  e where  e.categoryLevel3Id=?1")
    public List<EasybuyProduct> findEasybuyProductsByCategoryLevel3Id(Long id, Pageable pageable);


    //获取一级分类下的数量
    @Query("select count (e.id) from  EasybuyProduct  e where  e.categoryLevel1Id=?1")
    Integer getNumEasybuyProductsByCategoryLevel1Id(Long id);

    //获取二级分类下的数量
    @Query("select count (e.id) from  EasybuyProduct  e where  e.categoryLevel2Id=?1")
    Integer getNumEasybuyProductsByCategoryLevel2Id(Long id);
    //获取三级分类下的数量
    @Query("select count (e.id) from  EasybuyProduct  e where  e.categoryLevel3Id=?1")
    Integer getNumEasybuyProductsByCategoryLevel3Id(Long id);

    //产生订单库存的变化
    @Modifying
    @Query("UPDATE EasybuyProduct e set e.stock=e.stock-?1 where e.id=?2")
    void setEasybuyProductStock(Long quantity, Long id);

    //查看商品的总数
    @Query("select count (e.id) from EasybuyProduct  e")
    Integer getEasybuyProductNum();

    //根据id删除商品
    @Modifying
    @Override
    void deleteById(Long aLong);



    //添加商品
    @Modifying
    @Override
    <S extends EasybuyProduct> S save(S s);

    //修改商品
    @Modifying
    @Query("UPDATE EasybuyProduct  e set e.name=?1,e.fileName=?2,e.price=?3,e.stock=?4,e.description=?5,e.categoryLevel1Id=?6,e.categoryLevel2Id=?7,e.categoryLevel3Id=?8 where e.id=?9")
     void UpdateProductById(String name, String fileName, double price, Long stock, String descripton, Long categoryLevel1Id, Long categoryLevel2Id, Long categoryLevel3Id, Long id);
}
