package com.ljp.mapper;

import com.ljp.entity.ExcelEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestMapper {
    List<ExcelEntity> getList(@Param("paperId") String paperId);

    List<ExcelEntity> getQuestionList(@Param("paperId")String paperId);

    List<ExcelEntity> getParentId(@Param("shopCode")String shopCode, @Param("parentId")String parent_id);
}
