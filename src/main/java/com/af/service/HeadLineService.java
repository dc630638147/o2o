package com.af.service;

import com.af.model.dto.HeadLineVo;
import com.af.model.pojo.HeadLine;

import java.util.List;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/15 3:13
 */
public interface HeadLineService {
    public List<HeadLine> getHeadLineList(HeadLineVo vo);
}
