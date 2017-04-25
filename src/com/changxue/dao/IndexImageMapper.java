package com.changxue.dao;

import com.changxue.model.IndexImage;
import com.changxue.model.IndexImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IndexImageMapper {
    int countByExample(IndexImageExample example);

    int deleteByExample(IndexImageExample example);

    int deleteByPrimaryKey(String imageurl);

    int insert(IndexImage record);

    int insertSelective(IndexImage record);

    List<IndexImage> selectByExample(IndexImageExample example);

    IndexImage selectByPrimaryKey(String imageurl);

    int updateByExampleSelective(@Param("record") IndexImage record, @Param("example") IndexImageExample example);

    int updateByExample(@Param("record") IndexImage record, @Param("example") IndexImageExample example);

    int updateByPrimaryKeySelective(IndexImage record);

    int updateByPrimaryKey(IndexImage record);
}