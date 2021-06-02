package com.project.dps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

    @RequestMapping("/index")
    public String index() {
        return "redirect:/scenarios/";
    }

    @RequestMapping("/test1")
    public String test1() {
        return "/admin/edit-scenario";
    }

    @RequestMapping("/test2")
    public String test2() {
        return "/community/fnq";
    }

    @RequestMapping("/test3")
    public String test3() {
        return "/community/notice";
    }

    @RequestMapping("/test4")
    public String test4() {
        return "/community/notice1";
    }

    @RequestMapping("/test5")
    public String test5() {
        return "/community/notice2";
    }

}
