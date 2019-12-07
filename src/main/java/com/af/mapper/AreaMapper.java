package com.af.mapper;

import com.af.model.pojo.Area;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/7 21:44
 */
@Mapper
public interface AreaMapper {
    List<Area> selectTest();
}
