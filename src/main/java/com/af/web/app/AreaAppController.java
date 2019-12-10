package com.af.web.app;

import com.af.model.pojo.Area;
import com.af.service.AreaService;
import com.af.utils.JSONResult;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/11 1:07
 */
@RestController
@RequestMapping("/app/area")
public class AreaAppController {

    @Autowired
    private AreaService areaService;

    @GetMapping("/getAllArea")
    public JSONResult getAllArea() {
        List<Area> allAreaList = areaService.getAllArea();
        if (CollectionUtils.isEmpty(allAreaList)) {
            return JSONResult.errorMsg("数据不存在");
        }
        return JSONResult.ok(allAreaList);

    }
}
