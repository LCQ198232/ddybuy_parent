package com.ddybuy.ddybuy_manager_web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddybuy.ddybuy_manager_web.until.FastdfsUtil;
import com.ddybuy.entity.TbContent;
import com.ddybuy.service.TbContentService;
import com.ddybuy.until.DataGridResult;
import com.ddybuy.until.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TbContentController {

    @Autowired
    private FastdfsUtil fastdfsUtil;

    @Reference(interfaceClass = TbContentService.class)
    private TbContentService tbContentService;

    @RequestMapping("/addContent")
    @ResponseBody
    public Map<String, String> addContent(TbContent tbContent, @RequestParam(value = "pfile") MultipartFile file){
        HashMap<String,String> map=new HashMap<>();
        try {
            //上传图片到图片服务器
            //获取文件名
            String filename = file.getOriginalFilename();
            //获取文件后缀
            String expname=filename.substring(filename.lastIndexOf(".")+1);
            //上传图片
            String savefilename = fastdfsUtil.uploadFile(file.getBytes(), expname);
            if (savefilename!=null){
                tbContent.setPic(savefilename);
                //添加
                int i = tbContentService.addContent(tbContent);
                if (i>0){
                     map.put("result","1");
                }else {
                    //添加失败 需删除上传的图片
                    map.put("result","0");
                    //需删除上传的图片
                    fastdfsUtil.deleteFile("group1", savefilename);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }


    @RequestMapping("/getAllContent")
    @ResponseBody
    public DataGridResult<TbContent> getAllContent(Page page){
        //掉服务
        return  tbContentService.getContent(page);
    }


    @RequestMapping("/delContentOneOrList")
    @ResponseBody
    public String getAllContent(Integer[] ids){
        //掉服务
        int i=tbContentService.deleteOneOrListContent(ids);
        for (Integer id:ids){
            TbContent content = tbContentService.getSingleContent((long) id);
            fastdfsUtil.deleteFile("group1",content.getPic());
        }
        return "{\"result\":"+i+"}";
    }

    //查询单条(回显使用)
    @RequestMapping("/getSingleContent")
    @ResponseBody
    public TbContent getSingleContentCategory(Long id, HttpSession session){
        TbContent singleContentCategory = tbContentService.getSingleContent(id);
        session.setAttribute("tbContent",singleContentCategory);
        return singleContentCategory;
    }

    //修改操作
    @RequestMapping("/updateContent")
    @ResponseBody
    public Map<String, String> updateContent(@RequestParam(value = "pfile") MultipartFile file,HttpSession session){
        HashMap<String,String> map=new HashMap<>();

        TbContent tbContent = (TbContent)session.getAttribute("tbContent");
        try {
            //上传图片到图片服务器
            //获取文件名
            String filename = file.getOriginalFilename();
            //获取文件后缀
            String expname=filename.substring(filename.lastIndexOf(".")+1);
            //上传图片
            String savefilename = fastdfsUtil.uploadFile(file.getBytes(), expname);
            //修改的同时删除原有图片
              fastdfsUtil.deleteFile("group1",tbContent.getPic());
            if (savefilename!=null){
                tbContent.setPic(savefilename);
                //修改的同时删除原有图片
                int i = tbContentService.updateContent(tbContent);
                if (i>0){
                    map.put("result","1");
                }else {
                    //修改失败 需删除上传的图片
                    map.put("result","0");
                    //需删除上传的图片
                    fastdfsUtil.deleteFile("group1", savefilename);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

}
