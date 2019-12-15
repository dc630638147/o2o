package com.af.service.impl;

import com.af.mapper.ShopCategoryMapper;
import com.af.model.dto.ShopCategoryVo;
import com.af.model.pojo.ShopCategory;
import com.af.service.ShopCategoryService;
import org.apache.commons.collections.CollectionUtils;
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

    @Override
    public List<ShopCategory> getByParentId(Integer parentId) {
        if(parentId == 0){
            //查询所有
            List<ShopCategory> shopCategories = shopCategoryMapper.selectAll();
            return shopCategories;
        }else{
            //传入的是一级分类的id
            Example example = new Example(ShopCategory.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("parentId",parentId);
            List<ShopCategory> shopCategories = shopCategoryMapper.selectByExample(example);
            return shopCategories;
        }
    }
}
