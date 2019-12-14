package com.af.web.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/15 3:36
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/frontend/frontindex");
        return mv;
    }
}
