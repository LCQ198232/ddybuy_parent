package com.ddybuy.datailitem_web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddybuy.service.SolrService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class SolrItemController {

    @Reference(interfaceClass = SolrService.class,timeout = 5000)
    public SolrService solrService;

    @RequestMapping("/getflag")
    @ResponseBody
    public String getflag(){
        //数据库中的数据导入索引库中
        boolean flag = solrService.importIndex();
        return flag+"";
    }


    //查询业务
    @RequestMapping("/search")
    @CrossOrigin
    public String search( @RequestParam(value = "title",defaultValue ="")String title,
                          @RequestParam(value = "pageIndex",defaultValue ="1")Integer pageIndex, Model model){
        System.out.println(title+"   "+pageIndex);
        //掉业务
        Map<String, Object> map = solrService.searchProduct(title, pageIndex);

        //存储作用域
        model.addAttribute("map",map);
        //回显数据
        model.addAttribute("title",title);

        return "searchList";
    }

}
