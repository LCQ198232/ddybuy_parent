package com.ddybuy.ddybuy_common_service.impl;

import com.alibaba.dubbo.config.annotation.Service;

import com.ddybuy.SolrEntity.SolrProduct;
import com.ddybuy.mapper.TbItemMapper;
import com.ddybuy.service.SolrService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service(interfaceClass = SolrService.class)
public class SolrItemServiceImpl implements SolrService{

    @Value("${solr.pageSize}")
    private Integer pageSize;//分页参数在配置中设置

    //注入solrClient对象
    @Autowired
    private SolrClient solrClient;

    @Autowired(required = false)
    private TbItemMapper tbItemMapper;


    //将数据库数据全部导入搜素服务器 solr
    @Override
    public boolean importIndex() {
        try {
            //1.查询数据库中所有记录
            List<SolrProduct> list=tbItemMapper.getAllSolrProduct();
            //2.将所有记录添加到索引库:
            // 操作solr:springboot整合solr
            // a.导依赖  (已经在实体工程中导)
            // b.在springboot工程中添加solr的配置
            //spring.data.solr.host=http://localhost:8081/solr
            // c.注入solrClient对象即可操作solr
                solrClient.addBeans(list);
                solrClient.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    //搜索业务实现
    /*
    condition 查询条件
    page  页码
     */
    @Override
    public Map<String, Object> searchProduct(String condition, Integer page) {

        Map<String, Object> map =null;
        try {
        //封装查询条件
        SolrQuery query=new SolrQuery();
        //设置查询条件
        if (condition.trim().equals("")||condition==null)
            query.set("q","title:*");
        else
            query.set("q","title:"+condition);
        //设置分页
        //2.分页
        query.setStart((page-1)*pageSize);  //起始位置
        query.setRows(pageSize);//页大小

        //排序
         query.setSort("price",SolrQuery.ORDER.asc);

         //查询
         QueryResponse response = solrClient.query(query);

           //获取当前业数据
            List<SolrProduct> list = response.getBeans(SolrProduct.class);
            //获取总行数
            long totalRecords=response.getResults().getNumFound();
            //求总页数
            int totalPage=(int)(Math.ceil(totalRecords*1.0/pageSize));
            //创建map
            map=new HashMap<>();

            map.put("rows",list);
            map.put("totalPage",totalPage);
            map.put("totalRecords",totalRecords);

            return map;
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
