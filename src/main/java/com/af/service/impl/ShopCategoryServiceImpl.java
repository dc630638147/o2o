package com.af.service.impl;

import com.af.mapper.ShopCategoryMapper;
import com.af.model.pojo.ShopCategory;
import com.af.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/11 1:01
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Autowired
    private ShopCategoryMapper shopCategoryMapper;

    @Override
    public List<ShopCategory> getAllCategory() {
        return shopCategoryMapper.selectAll();
    }
}
