package com.ddybuy.service;

import java.util.Map;

public interface SolrService {
    //将数据库中数据导入到索引库
    public boolean importIndex();

    //搜索业务
    public Map<String,Object> searchProduct(String condition, Integer page);
}
