package com.af.web.app;

import com.af.model.dto.HeadLineVo;
import com.af.model.pojo.HeadLine;
import com.af.service.HeadLineService;
import com.af.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/15 3:13
 */
@Controller
@RequestMapping("/headLineApp")
public class HeadLineController {

    @Autowired
    private HeadLineService headLineService;

    @RequestMapping("/getHeadLineList")
    @ResponseBody
    private JSONResult getHeadLineList(){
        HeadLineVo vo = new HeadLineVo();
        vo.setEnableStatus(1);//正常
        List<HeadLine> headLineList = headLineService.getHeadLineList(vo);
        return JSONResult.ok(headLineList);
    }
}
