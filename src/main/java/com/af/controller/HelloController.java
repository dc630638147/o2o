package com.af.controller;

import com.af.mapper.AreaMapper;
import com.af.model.pojo.Area;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/7 16:59
 */
@Controller
@Slf4j
public class HelloController {
    @Autowired
    private AreaMapper areaMapper;

    @RequestMapping("/hello1")
    public ModelAndView hello() {
        ModelAndView view = new ModelAndView();
        view.addObject("val", ":hahah");
        view.setViewName("hello");
        return view;
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("val", ":haaaaahah");
        return "hello";
    }

    @RequestMapping("/test")
    @ResponseBody
    private List<Area> test() {
        log.info("info");
        log.error("error");
        log.debug("debug");
        return areaMapper.selectAll();
    }
}
