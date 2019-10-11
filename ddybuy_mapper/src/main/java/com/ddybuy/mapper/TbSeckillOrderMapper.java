package com.ddybuy.mapper;

import com.ddybuy.entity.TbSeckillOrder;
import com.ddybuy.entity.TbSeckillOrderExample;
import java.util.List;

public interface TbSeckillOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbSeckillOrder record);

    int insertSelective(TbSeckillOrder record);

    List<TbSeckillOrder> selectByExample(TbSeckillOrderExample example);

    TbSeckillOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbSeckillOrder record);

    int updateByPrimaryKey(TbSeckillOrder record);
}