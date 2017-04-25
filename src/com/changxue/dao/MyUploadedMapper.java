package com.changxue.dao;

import com.changxue.model.MyUploaded;
import com.changxue.model.MyUploadedExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyUploadedMapper {
    int countByExample(MyUploadedExample example);

    int deleteByExample(MyUploadedExample example);

    int insert(MyUploaded record);

    int insertSelective(MyUploaded record);

    List<MyUploaded> selectByExample(MyUploadedExample example);

    int updateByExampleSelective(@Param("record") MyUploaded record, @Param("example") MyUploadedExample example);

    int updateByExample(@Param("record") MyUploaded record, @Param("example") MyUploadedExample example);
}