package com.ddybuy.mapper;

import com.ddybuy.entity.TbContentCategory;
import com.ddybuy.entity.TbContentCategoryExample;
import java.util.List;

public interface TbContentCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbContentCategory record);

    int insertSelective(TbContentCategory record);

    List<TbContentCategory> selectByExample(TbContentCategoryExample example);

    TbContentCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbContentCategory record);

    int updateByPrimaryKey(TbContentCategory record);

    //删除单条或多条广告信息
    int deleteMoreContentCategory(Integer[] ids);
}