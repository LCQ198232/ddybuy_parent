package com.ddybuy.ddybuy_common_service;

import com.ddybuy.service.SolrService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DdybuyCommonServiceApplicationTests {

@Autowired
private SolrService solrService;

    @Test
    public void contextLoads() {
        System.out.println(solrService.searchProduct("",1));
    }

}
