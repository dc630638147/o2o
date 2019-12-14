package com.af.model.dto;

import com.af.model.pojo.ShopCategory;
import lombok.Data;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/14 11:56
 */
@Data
public class ShopCategoryVo extends ShopCategory {
    private Integer pageNum;//第几页
    private Integer pageSize;//每页的条数
}
