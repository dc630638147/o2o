package com.af.service;

import com.af.model.dto.ProductVo;
import com.af.model.pojo.Product;
import com.af.utils.JSONResult;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/14 15:57
 */
public interface ProductService {
    public JSONResult addproduct(Product product, CommonsMultipartFile productImg,
                                 CommonsMultipartFile productDescImg);

    public JSONResult getProductList(ProductVo vo);

    public JSONResult delete(Integer productId);
}
