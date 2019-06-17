package com.luo.springcloudeurekaclientnotice.Service;

import com.luo.springcloudeurekaclientnotice.entity.Document;

import java.util.List;

public interface SolrService {

    public List<Document> getProducts(String queryString) throws Exception;
}
