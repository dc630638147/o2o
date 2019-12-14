package com.af.service;

import com.af.model.dto.ProductCategoryVo;
import com.af.model.pojo.ProductCategory;
import com.af.utils.JSONResult;

import java.util.List;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/14 12:14
 */
public interface ProductCategoryService {

    /**
     * 根据条件查询
     * @param vo
     * @return
     */
    public List<ProductCategory> getProductCategory(ProductCategoryVo vo);

    /**
     * 删除
     * @param productCategoryId
     * @return
     */
    public JSONResult delete(Integer productCategoryId);

    /**
     * 批量增加
     * @param list
     * @return
     */
    public JSONResult batchAdd(List<ProductCategory> list);

}
