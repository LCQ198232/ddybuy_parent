package com.ddybuy.ddybuy_manager_web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddybuy.entity.TbContentCategory;
import com.ddybuy.service.TbContentCategoryService;
import com.ddybuy.until.DataGridResult;
import com.ddybuy.until.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TbContentCategoryController {


    @Reference(interfaceClass = TbContentCategoryService.class)
    private TbContentCategoryService tbContentCategoryService;

    @RequestMapping("/getAllTbContentCategory")
    @ResponseBody
    public DataGridResult<TbContentCategory> getAllTbContentCategory(Page page){
        //掉服务
        return  tbContentCategoryService.getContentCategory(page);
    }

    @RequestMapping("/addContentCategory")
    @ResponseBody
    public String insertContentCategory(TbContentCategory tbContentCategory){
        int i = tbContentCategoryService.insertContentCategory(tbContentCategory);

        return "{\"result\":"+i+"}";
    }

    //删除单条或多条广告信息
    @RequestMapping("/delContentCategoryOneOrList")
     @ResponseBody
    public String delContentCategoryOneOrList(Integer[] ids){
        int i = tbContentCategoryService.deleteOneOrListContentCategory(ids);
        return "{\"result\":"+i+"}";
    }

    //查询单条(回显使用)
    @RequestMapping("/getSingleContentCategory")
    @ResponseBody
    public TbContentCategory getSingleContentCategory(Long id){
        TbContentCategory singleContentCategory = tbContentCategoryService.getSingleContentCategory(id);
        return singleContentCategory;
    }
    //修改广告类型信息
    @RequestMapping("/updateContentCategory")
    @ResponseBody
    public String updateContentCategory(TbContentCategory tbContentCategory){
        int i = tbContentCategoryService.updateContentCategory(tbContentCategory);
        return "{\"result\":"+i+"}";
    }

    @RequestMapping("/getCategory")
    @ResponseBody
    public List<TbContentCategory> getCategory(){
        List<TbContentCategory> list = tbContentCategoryService.getCategory();
        return list;
    }
}
