package com.ddybuy.solr;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;

public class SolrTest3 {
    //修改
    public static void main(String[] args) throws IOException, SolrServerException {
          String solrUrl="http://localhost:8081/solr/";
        //创建HttpSolrClient对象才做solr
        HttpSolrClient solrClient=new HttpSolrClient.Builder(solrUrl+"new_core").
                withConnectionTimeout(10000).withSocketTimeout(60000).build();
        //先删除,再添加
        solrClient.deleteByQuery("name:小王发");
        //添加
        //创建solrInputducment对象添加一行
        SolrInputDocument solrInputFields=new SolrInputDocument();
        solrInputFields.addField("xh","101");
        solrInputFields.addField("name","李四");
        solrInputFields.addField("age","101");
        //添加到索引库
        solrClient.add(solrInputFields);
        //提交
        solrClient.commit();
        System.out.println("修改成功");


    }
}
