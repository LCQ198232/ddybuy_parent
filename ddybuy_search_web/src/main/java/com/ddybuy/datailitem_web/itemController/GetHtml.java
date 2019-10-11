package com.ddybuy.datailitem_web.itemController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddybuy.service.TbItemService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class GetHtml {
    @Reference(interfaceClass = TbItemService.class,timeout = 50000)
    private TbItemService tbItemService;
    @RequestMapping("/getHtml")
    @ResponseBody
    public  String getHtml() {
        boolean flag = tbItemService.productHtml();
        System.out.println("生成静态页面"+flag);
        return flag+"";
    }
}
