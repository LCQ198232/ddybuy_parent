package com.ddybuy.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;

public class SolrTest4 {
    //查询
    public static void main(String[] args) throws IOException, SolrServerException {
          String solrUrl="http://localhost:8081/solr/";
        //创建HttpSolrClient对象才做solr
        HttpSolrClient solrClient=new HttpSolrClient.Builder(solrUrl+"new_core").
                withConnectionTimeout(10000).withSocketTimeout(60000).build();

        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("q","*:*");//设置查询条件
        solrQuery.setSort("xh", SolrQuery.ORDER.asc);//设置排序
        solrQuery.setStart(0);//设置页号
        solrQuery.setRows(3);//设置页大小
        QueryResponse query = solrClient.query(solrQuery);
        //提交
        solrClient.commit();
        System.out.println("查询成功");
        System.out.println(query);

    }
}
