package com.af.service.impl;

import com.af.mapper.ProductCategoryMapper;
import com.af.mapper.ProductMapper;
import com.af.model.dto.ProductCategoryVo;
import com.af.model.pojo.Product;
import com.af.model.pojo.ProductCategory;
import com.af.service.ProductCategoryService;
import com.af.utils.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/14 12:15
 */
@Service
@Slf4j
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ProductCategory> getProductCategory(ProductCategoryVo vo) {
        Example pcExample = new Example(ProductCategory.class);
        Example.Criteria criteria = pcExample.createCriteria();
        pcExample.orderBy("priority").desc();
        if (vo != null) {
            if (vo.getShopId() != null) {
                criteria.andEqualTo("shopId", vo.getShopId());
            }
        }
        return productCategoryMapper.selectByExample(pcExample);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public JSONResult delete(Integer productCategoryId) {
        try {
            //TODO 要先校验下面是否还有商品
            Example productEx = new Example(Product.class);
            productEx.createCriteria().andEqualTo("productCategortId",productCategoryId).
                    andEqualTo("enableStatus",0);
            List<Product> products = productMapper.selectByExample(productEx);
            if(CollectionUtils.isNotEmpty(products)){
                return JSONResult.errorMsg("还存在此分类商品，不能删除");
            }

            ProductCategory pc = new ProductCategory();
            pc.setProductCategoryId(productCategoryId);
            int delete = productCategoryMapper.delete(pc);
            if (delete < 1) {
                return JSONResult.errorMsg("删除失败");
            }
        } catch (Exception e) {
            log.info("删除商品分类异常 {}：", e.getMessage());
            return JSONResult.errorMsg("删除异常");
        }
        return JSONResult.ok();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public JSONResult batchAdd(List<ProductCategory> list) {
        if (CollectionUtils.isEmpty(list)) {
            return JSONResult.errorMsg("请填写数据");
        }
        try {
            for (ProductCategory l : list) {
                if (StringUtils.isNotBlank(l.getProductCategoryName()) && l.getPriority()!=null){
                    l.setCreateTime(new Date());
                    l.setLastEditTime(new Date());
                    int i = productCategoryMapper.insertSelective(l);
                    if (i < 1) {
                        throw new RuntimeException("数据增加失败");
                    }
                }
            }
        } catch (Exception e) {
            log.info("商品分类增加异常:{}", e.getMessage());
            return JSONResult.errorMsg("商品分类增加异常");
        }
        return JSONResult.ok();
    }
}
