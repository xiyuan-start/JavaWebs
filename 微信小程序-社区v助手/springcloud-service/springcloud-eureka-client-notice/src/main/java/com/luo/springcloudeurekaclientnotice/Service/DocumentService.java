package com.luo.springcloudeurekaclientnotice.Service;

import com.luo.springcloudeurekaclientnotice.Dao.DocumentDao;
import com.luo.springcloudeurekaclientnotice.entity.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

   @Autowired
    DocumentDao documentDao;


   //分页获取
   public List<Document>GetDocumentByPage(Integer page,Integer size)
   {
       Pageable pageable =new PageRequest(page,size);
       return documentDao.GetAllByPage(pageable);
   }

   //根据Id 获取
    public Document GetDocumentById(Integer id)
    {
        return documentDao.GetOneById(id);
    }


    //保存
    public void SaveDocument(Document document)
    {
        documentDao.save(document);
    }


    //根据标题模糊查询
    public List<Document> GetDocumentsByTitle(String title)
    {
        return documentDao.GetMoreBytitle(title);
    }
}
