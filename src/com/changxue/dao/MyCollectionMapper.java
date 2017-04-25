package com.changxue.dao;

import com.changxue.model.MyCollection;
import com.changxue.model.MyCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyCollectionMapper {
    int countByExample(MyCollectionExample example);

    int deleteByExample(MyCollectionExample example);

    int insert(MyCollection record);

    int insertSelective(MyCollection record);

    List<MyCollection> selectByExample(MyCollectionExample example);

    int updateByExampleSelective(@Param("record") MyCollection record, @Param("example") MyCollectionExample example);

    int updateByExample(@Param("record") MyCollection record, @Param("example") MyCollectionExample example);
}