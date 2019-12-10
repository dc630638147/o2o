package com.af.service.impl;

import com.af.mapper.AreaMapper;
import com.af.model.pojo.Area;
import com.af.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/11 1:10
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Area> getAllArea() {
        return areaMapper.selectAll();
    }
}
