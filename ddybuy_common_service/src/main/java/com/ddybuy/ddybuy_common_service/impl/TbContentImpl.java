package com.ddybuy.ddybuy_common_service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddybuy.entity.TbContent;
import com.ddybuy.entity.TbContentExample;
import com.ddybuy.mapper.TbContentMapper;
import com.ddybuy.service.TbContentService;
import com.ddybuy.until.DataGridResult;
import com.ddybuy.until.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service(interfaceClass = TbContentService.class)
@Component
public class TbContentImpl implements TbContentService{

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    private TbContentMapper tbContentMapper;


    @Override
    public int addContent(TbContent tbContent) {
        int tmpe=-1;
        try {
            //添加数据
            tmpe=tbContentMapper.insertSelective(tbContent);

            //重新设置缓存
            this.setBuffer();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return tmpe;
    }

    @Override
    public DataGridResult<TbContent> getContent(Page page) {

        //启动分页
        PageHelper.startPage(page.getPage(),page.getRows());
        //list
        List<TbContent> list = tbContentMapper.selectByExample(new TbContentExample());
        //pageinfo
        PageInfo<TbContent> pageInfo =new PageInfo<>(list);
        //DataGridResult实体类
        return new DataGridResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public int deleteOneOrListContent(Integer[] ids) {
        int tmpe=-1;
        try {
            //删除数据
            tmpe=tbContentMapper.deleteMoreContent(ids);

            //重新设置缓存
            this.setBuffer();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return tmpe;
    }



    @Override
    public TbContent getSingleContent(Long id) {
        return tbContentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateContent(TbContent tbContent) {
        int tmpe=-1;
        try {
            //修改数据
            tmpe=tbContentMapper.updateByPrimaryKeySelective(tbContent);

            //重新设置缓存
            this.setBuffer();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return tmpe;
    }



    @Override
    public List<TbContent> getAllContent() {
        List<TbContent> list=null;

        ValueOperations<String,Object> option = redisTemplate.opsForValue();
        if(this.redisTemplate.hasKey("contentkey")){
            System.out.println("从缓存中获取......");
            //存在，获取缓存中的值
            list=(List<TbContent>)option.get("contentkey");
        }else
        {
            System.out.println("读取数据库.....");
            //不存在，查询数据库并缓存
            list=this.setBuffer();
        }
        return list;
    }



    //设置缓存中的数据
    public List<TbContent> setBuffer(){

        ValueOperations<String,Object> option=redisTemplate.opsForValue();
        //不存在，查询数据库并缓存
        TbContentExample tbContentExample=new TbContentExample();
        TbContentExample.Criteria criteria =tbContentExample.createCriteria();
        criteria.andStatusEqualTo("1");
        List<TbContent> list=tbContentMapper.selectByExample(tbContentExample);
        //存入缓存   //1天
        option.set("contentkey",list,1,TimeUnit.DAYS);
        return  list;
    }

}
