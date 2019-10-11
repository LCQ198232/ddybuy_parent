package com.ddybuy.service;

import com.ddybuy.entity.TbContentCategory;
import com.ddybuy.until.DataGridResult;
import com.ddybuy.until.Page;

import java.util.List;

public interface TbContentCategoryService {

    //查询所有广告类别信息
    DataGridResult<TbContentCategory> getContentCategory(Page page);

    //查询所有广告类别信息
    List<TbContentCategory> getCategory();

    //添加广告信息
    int insertContentCategory(TbContentCategory tbContentCategory);

    //删除单条或多条广告信息
    int deleteOneOrListContentCategory(Integer[] ids);

    //通过id查询单条(回显使用)
    TbContentCategory getSingleContentCategory(Long id);

    //修改广告类型信息
    int updateContentCategory(TbContentCategory tbContentCategory);
}
