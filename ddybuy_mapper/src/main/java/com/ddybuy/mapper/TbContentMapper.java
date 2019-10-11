package com.ddybuy.mapper;

import com.ddybuy.entity.TbContent;
import com.ddybuy.entity.TbContentExample;
import java.util.List;

public interface TbContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbContent record);

    int insertSelective(TbContent record);

    List<TbContent> selectByExample(TbContentExample example);

    TbContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbContent record);

    int updateByPrimaryKey(TbContent record);

    //删除单条或多条广告信息
    int deleteMoreContent(Integer[] ids);
}