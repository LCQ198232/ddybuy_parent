package com.ddybuy.protocal_web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddybuy.entity.TbContent;
import com.ddybuy.service.TbContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TbContentController {

    @Reference(interfaceClass = TbContentService.class,timeout = 10000)
    private TbContentService tbContentService;

    @RequestMapping("/index")
    @CrossOrigin  //支持跨域访问  跨系统
    public String getContent(Model model){
        List<TbContent> list = tbContentService.getAllContent();
        model.addAttribute("list",list);

        return "index";
    }
}
