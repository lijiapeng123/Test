package com.ljp.service;

import com.ljp.entity.ExcelEntity;

import java.util.List;
import java.util.Map;

public interface TestService {
    List<ExcelEntity> getList(String paperId);

    List<ExcelEntity> getParentId(String shopCode, String parent_id);
}
