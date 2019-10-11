package com.ddybuy.solr;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;

public class SolrTest2 {
    //删除
    public static void main(String[] args) throws IOException, SolrServerException {
          String solrUrl="http://localhost:8081/solr/";
        //创建HttpSolrClient对象才做solr
        HttpSolrClient solrClient=new HttpSolrClient.Builder(solrUrl+"new_core").
                withConnectionTimeout(10000).withSocketTimeout(60000).build();
        solrClient.deleteByQuery("name:张三");
        //提交
        solrClient.commit();
        System.out.println("删除成功");


    }
}
