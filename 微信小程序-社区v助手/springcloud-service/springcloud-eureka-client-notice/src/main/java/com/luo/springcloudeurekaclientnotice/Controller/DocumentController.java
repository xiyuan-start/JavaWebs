package com.luo.springcloudeurekaclientnotice.Controller;


import com.luo.springcloudeurekaclientnotice.Service.DocumentService;
import com.luo.springcloudeurekaclientnotice.Service.ImpSolrService;
import com.luo.springcloudeurekaclientnotice.Service.SolrService;
import com.luo.springcloudeurekaclientnotice.Tools.WebTool.ResultMent;
import com.luo.springcloudeurekaclientnotice.Tools.WebTool.SendJson;
import com.luo.springcloudeurekaclientnotice.entity.Document;
import com.luo.springcloudeurekaclientnotice.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @Autowired
    ImpSolrService impSolrService;

    //分页获取文档
    @GetMapping("/GetDocumentByPage")
    public void GetNoticeByPage(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(required = true) Integer page,
                                @RequestParam(required = true) Integer size


    ) throws IOException {



        List<Document>list=documentService.GetDocumentByPage(page,size);
        ResultMent resultMent = new ResultMent();
        resultMent.setStatus(page+list.size());
        resultMent.setData(list);
        SendJson.sendJsonObject(request, response, resultMent);

    }


    //根据Id获取文档
    //并且增加阅读数
    @GetMapping("/GetDocumentById")
    public void GetDocumentById(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(required = true)Integer id


    ) throws IOException {

        Document document=documentService.GetDocumentById(id);
        document.addCount();
        documentService.SaveDocument(document);
        ResultMent resultMent = new ResultMent();
        resultMent.setData(document);
        resultMent.setStatus(1);
        SendJson.sendJsonObject(request, response, resultMent);
    }


    //根据标题 模糊查询获取文档
    @GetMapping("/GetDocumentBytitle")
    public void GetDocumentBytitle(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(required = true)String title


    ) throws IOException ,Exception{

        List<Document>list=impSolrService.getProducts(title);
        ResultMent resultMent = new ResultMent();
        resultMent.setData(list);
        SendJson.sendJsonObject(request, response, resultMent);
    }




}
