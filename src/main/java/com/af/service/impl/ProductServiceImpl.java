package com.af.service.impl;

import com.af.mapper.ProductImgMapper;
import com.af.mapper.ProductMapper;
import com.af.model.dto.ProductVo;
import com.af.model.pojo.Product;
import com.af.model.pojo.ProductImg;
import com.af.service.ProductService;
import com.af.utils.ImageUtil;
import com.af.utils.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/14 15:58
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Value("${rootFilePath}")
    private String rootFilePath;
    @Value("${productThumbnail}")
    private String productThumbnail;
    @Value("${productDesc}")
    private String productDesc;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductImgMapper productImgMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public JSONResult addproduct(Product product, CommonsMultipartFile productImg,
                                 CommonsMultipartFile productDescImg) {
        try {
            //先上传数据
            String path = ImageUtil.generateThumbnail(productImg, rootFilePath, productThumbnail);
            product.setImgAddr(path);
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            product.setEnableStatus(0);
            int i = productMapper.insertSelective(product);
            if (i < 1) {
                throw new RuntimeException("数据插入失败");
            }
            //上传详情图
            String descPath = ImageUtil.generateThumbnailDesc(productDescImg, rootFilePath, productDesc);
            ProductImg pi = new ProductImg();
            pi.setImgAddr(descPath);
            pi.setProductId(product.getProductId());
            pi.setCreateTime(new Date());
            //插入
            int piRes = productImgMapper.insertSelective(pi);
            if (piRes < 1) {
                throw new RuntimeException("详情图插入失败");
            }
        } catch (Exception e) {
            log.info("商品添加异常:{}", e.getMessage());
            return JSONResult.errorMsg("商品添加异常");
        }
        return JSONResult.ok();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public JSONResult getProductList(ProductVo vo) {
        Example productExample = new Example(Product.class);
        Example.Criteria criteria = productExample.createCriteria();
        if(vo !=null){
            if(vo.getShopId() != null){
                criteria.andEqualTo("shopId",vo.getShopId());
            }
            if(vo.getEnableStatus()!=null){
                criteria.andEqualTo("enableStatus",vo.getEnableStatus());
            }
        }
        return JSONResult.ok(productMapper.selectByExample(productExample));
    }

    @Override
    public JSONResult delete(Integer productId) {
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId",productId);
        Product p = new Product();
        p.setEnableStatus(-1);//下架
        int i = productMapper.updateByExampleSelective(p,example);
        if(i < 0){
            return JSONResult.errorMsg("删除失败");
        }
        return JSONResult.ok();
    }
}
