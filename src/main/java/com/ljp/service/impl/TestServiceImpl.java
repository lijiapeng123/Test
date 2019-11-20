package com.ljp.service.impl;

import com.ljp.entity.ExcelEntity;
import com.ljp.mapper.TestMapper;
import com.ljp.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;

    @Override
    public List<ExcelEntity> getList(String paperId) {
        List<ExcelEntity> list = testMapper.getList(paperId);
        return list;
    }

    @Override
    public List<ExcelEntity> getParentId(String shopCode, String parent_id) {
        return testMapper.getParentId(shopCode,parent_id);
    }
}
