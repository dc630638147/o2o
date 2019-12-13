package com.af.model.dto;

import com.af.model.pojo.Shop;
import lombok.Data;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/12 23:37
 */
@Data
public class ShopVo extends Shop  {
   private Integer pageNum;//第几页
   private Integer pageSize;//每页的条数
}
