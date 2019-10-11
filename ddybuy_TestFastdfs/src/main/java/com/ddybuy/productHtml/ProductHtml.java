package com.ddybuy.productHtml;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//页面静态化
public class ProductHtml {
    //生成静态页面
    public static void main(String[] args) throws IOException, TemplateException {
           //创建configuration对象
        Configuration configuration=new Configuration(Configuration.getVersion());
        //2.2设置相关的参数
        //设置编码
        configuration.setDefaultEncoding("utf-8");
        //设置模板目录
        File file=new File("D:\\Program Files\\分布式源码\\ddybuy_parent\\ddybuy_TestFastdfs\\src\\main\\resources");
        configuration.setDirectoryForTemplateLoading(file);
        //2.3获取模板对象
        Template template=configuration.getTemplate("first.ftl");
        //2.4 创建模板对应的模型数据
        Map<String,Object> maps=new HashMap<>();
        maps.put("name","隔避老王");
        maps.put("content","我很优秀，请招我进公司，如果你相信，我还你一百w");
        maps.put("names", Arrays.asList("苹果","梨","桃","blanan"));
        //2.5 生成静态页面
        Writer w=new FileWriter("D:\\Program Files\\分布式源码\\ddybuy_parent\\ddybuy_TestFastdfs\\src\\main\\resources\\first.html");
        template.process(maps,w);
        //2.6关闭流
        w.close();
        System.out.println("生成静态页面成功");
    }
}
