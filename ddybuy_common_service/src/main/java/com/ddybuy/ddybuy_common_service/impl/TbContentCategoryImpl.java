package com.ddybuy.ddybuy_common_service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddybuy.entity.TbContentCategory;
import com.ddybuy.entity.TbContentCategoryExample;
import com.ddybuy.mapper.TbContentCategoryMapper;
import com.ddybuy.service.TbContentCategoryService;
import com.ddybuy.until.DataGridResult;
import com.ddybuy.until.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service(interfaceClass = TbContentCategoryService.class)
@Component   //创建对象
public class TbContentCategoryImpl implements TbContentCategoryService {
    //调用mapper
    @Autowired(required = false)
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public DataGridResult<TbContentCategory> getContentCategory(Page page) {

        //启动分页
        PageHelper.startPage(page.getPage(),page.getRows());
        //list
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(new TbContentCategoryExample());
        //pageinfo
        PageInfo<TbContentCategory> pageInfo =new PageInfo<>(list);
        //DataGridResult实体类
        return new DataGridResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public List<TbContentCategory> getCategory() {
        return tbContentCategoryMapper.selectByExample(new TbContentCategoryExample());
    }

    //添加广告信息
    @Override
    public int insertContentCategory(TbContentCategory tbContentCategory) {
        return tbContentCategoryMapper.insertSelective(tbContentCategory);
    }
      //删除单条或多条广告信息
    @Override
    public int deleteOneOrListContentCategory(Integer[] ids) {
        return tbContentCategoryMapper.deleteMoreContentCategory(ids);
    }
   //查询单条(回显使用)
    @Override
    public TbContentCategory getSingleContentCategory(Long id) {
        return tbContentCategoryMapper.selectByPrimaryKey(id);
    }
  //修改广告类型信息
    @Override
    public int updateContentCategory(TbContentCategory tbContentCategory) {
        return tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
    }
}
