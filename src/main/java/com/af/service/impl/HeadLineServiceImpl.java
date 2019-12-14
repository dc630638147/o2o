package com.af.service.impl;

import com.af.mapper.HeadLineMapper;
import com.af.model.dto.HeadLineVo;
import com.af.model.pojo.HeadLine;
import com.af.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/15 3:13
 */
@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    private HeadLineMapper headLineMapper;

    /**
     * 根据条件查询头条
     * @param vo
     * @return
     */
    public List<HeadLine> getHeadLineList(HeadLineVo vo){
        Example example = new Example(HeadLine.class);
        Example.Criteria criteria = example.createCriteria();
        if(vo !=null){
            //添加条件
            if(vo.getEnableStatus() !=null){
                criteria.andEqualTo("enableStatus",vo.getEnableStatus());
            }
        }
        return headLineMapper.selectByExample(example);
    }
}
