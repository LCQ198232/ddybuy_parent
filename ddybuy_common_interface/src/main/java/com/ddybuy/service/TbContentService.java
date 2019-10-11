package com.ddybuy.service;

import com.ddybuy.entity.TbContent;
import com.ddybuy.until.DataGridResult;
import com.ddybuy.until.Page;

import java.util.List;

public interface TbContentService {

    //添加广告
    int addContent(TbContent tbContent);

    //查询所有广告信息
    DataGridResult<TbContent> getContent(Page page);

    //删除单条或多条广告信息
    int deleteOneOrListContent(Integer[] ids);

    //通过id查询单条(回显使用)
    TbContent getSingleContent(Long id);

    //修改广告信息
    int updateContent(TbContent tbContent);

    //查询所有广告信息
    List<TbContent> getAllContent();
}
