package com.luo.springcloudeurekaclientnotice.Service;

import com.luo.springcloudeurekaclientnotice.entity.Document;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ImpSolrService implements SolrService {


    // 依赖注入HttpSolrServer
    @Autowired
    private SolrClient client;

    @Override
    public List<Document> getProducts(String queryString) throws Exception {

        // 创建SolrQuery对象
        SolrQuery query = new SolrQuery();

        if (StringUtils.isNotEmpty(queryString)) {
            query.setQuery("document_title:"+queryString);
        } else {
            query.setQuery("*:*");
        }


        QueryResponse response = client.query(query);
        // 查询出的结果
        SolrDocumentList results = response.getResults();
        // 记录总数
        long count = results.getNumFound();

        List<Document>documents=new ArrayList<>();
        Document document;

        // 获取高亮信息
        for (SolrDocument doc : results) {
            document=new Document();

            document.setId(new Integer(doc.get("id").toString()));
            document.setImgUrl(doc.get("document_img_url").toString());
            document.setFileUrl(doc.get("document_file_url").toString());
            document.setTitle(doc.get("document_title").toString());
            document.setCount(new Integer(doc.get("document_count").toString()));

            documents.add(document);
        }

        return documents;
    }
}
