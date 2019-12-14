package com.af.service.impl;

import com.af.mapper.ShopCategoryMapper;
import com.af.model.dto.ShopCategoryVo;
import com.af.model.pojo.ShopCategory;
import com.af.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
    public List<ShopCategory> getAllCategory(ShopCategoryVo vo) {
        Example shopCategoryExample = new Example(ShopCategory.class);
        Example.Criteria criteria = shopCategoryExample.createCriteria();
       if(vo !=null){
           if(vo.getShopCategoryId() != null){
               criteria.andEqualTo("shopCategoryId",vo.getShopCategoryId());
           }
           if(vo.getParentId() != null){
               criteria.andEqualTo("parentId",vo.getParentId());
           }
       }
        return shopCategoryMapper.selectByExample(shopCategoryExample);

    }
}
