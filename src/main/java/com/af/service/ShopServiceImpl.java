package com.af.service;

import com.af.exception.ShopOperationExcetion;
import com.af.mapper.ShopMapper;
import com.af.model.dto.ShopCategoryVo;
import com.af.model.dto.ShopVo;
import com.af.model.pojo.Area;
import com.af.model.pojo.PersonInfo;
import com.af.model.pojo.Shop;
import com.af.model.pojo.ShopCategory;
import com.af.service.impl.PersonInfoServiceImpl;
import com.af.utils.ImageUtil;
import com.af.utils.JSONResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/8 15:23
 */
@Slf4j
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;
    @Value("${rootFilePath}")
    private String rootFilePath;//总路径
    @Value("${shopThumbnail}")
    private String shopThumbnail;//店铺缩略图路径
    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private PersonService personService;

    /**
     * 增加店铺
     *
     * @param shop
     * @param
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public JSONResult addShop(Shop shop, CommonsMultipartFile shopImgFile) {
        //上传信息
        try {
            //上传图片
            String path = ImageUtil.generateThumbnail(shopImgFile, rootFilePath, shopThumbnail);
            shop.setShopImg(path);
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //插入
            int res = shopMapper.insertSelective(shop);
          //  int i = 10/0;
            if (res < 1) {
                log.info("店铺创建失败,插入res{}", res);
                throw new RuntimeException("店铺创建失败");
            }
        } catch (Exception e) {
            log.info("店铺创建异常:{}", e.getMessage());
            throw new ShopOperationExcetion("店铺创建异常");
        }
        return JSONResult.ok();
    }

    @Override
    public JSONResult allShopInfo(Integer shopId) {
        Shop shop = new Shop();
        shop.setShopId(shopId);
        List<Shop> shops = shopMapper.select(shop);
        if(CollectionUtils.isEmpty(shops)){
            return JSONResult.errorMsg("查询异常");
        }
        //
        List<Area> allArea = areaService.getAllArea();
        List<ShopCategory> allCategory = shopCategoryService.getAllCategory(null);
        JSONObject ob = new JSONObject();
        ob.put("shop",shops.get(0));
        ob.put("areaList",allArea);
        ob.put("shopCategoryList",allCategory);
        return new JSONResult(ob);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public JSONResult updateShop(Shop shop, CommonsMultipartFile shopImgFile) {
        //上传信息
        try {
            if (shopImgFile != null) {
                String path = ImageUtil.generateThumbnail(shopImgFile, rootFilePath, shopThumbnail);
                shop.setShopImg(path);
            }
            shop.setLastEditTime(new Date());
            //插入
            Example shopExample = new Example(Shop.class);
            Example.Criteria criteria = shopExample.createCriteria();

            criteria.andEqualTo("shopId", shop.getShopId());
            //criteria.andEqualTo("ownerId",2);
            int res =shopMapper.updateByExampleSelective(shop, shopExample);//shopMapper.updateByPrimaryKeySelective(shop);

            //  int i = 10/0;
            if (res < 1) {
                log.info("更新创建失败,res{}", res);
                throw new RuntimeException("店铺更新失败");
            }
        } catch (Exception e) {
            log.info("店铺更新异常:{}", e.getMessage());
            throw new ShopOperationExcetion("店铺更新异常");
        }
        return JSONResult.ok();
    }

    /**
     * 分页查找 ownerId不能为空
     * @param shopVo
     * @return
     */
    @Override
    public PageInfo<Shop> getShopPage(ShopVo shopVo) {

        Example shopExample = new Example(Shop.class);
        Example.Criteria criteria = shopExample.createCriteria();
        //店铺名模糊查询
        if(StringUtils.isNotBlank(shopVo.getShopName())){
            criteria.andLike("shopName","%"+shopVo.getShopName()+"%");
        }
        //商铺分类
        if(shopVo.getShopCategoryId() != null){
            criteria.andEqualTo("shopCategoryId",shopVo.getShopCategoryId());
        }
        //所属区域
        if(shopVo.getAreaId() != null){
            criteria.andEqualTo("areaId",shopVo.getAreaId());
        }
        //店铺状态
        if(shopVo.getEnableStatus() != null){
            criteria.andEqualTo("enableStatus",shopVo.getEnableStatus());
        }
        //商家id
        if(shopVo.getOwnerId() != null){
            criteria.andEqualTo("ownerId",shopVo.getOwnerId());
        }
        PageHelper.startPage(shopVo.getPageNum(),shopVo.getPageSize());
        List<Shop> shops = shopMapper.selectByExample(shopExample);
        PageInfo<Shop> pageInfo = new PageInfo<>(shops);
        return pageInfo;
    }
}
