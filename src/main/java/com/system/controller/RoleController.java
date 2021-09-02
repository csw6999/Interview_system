package com.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoleController {
    @RequestMapping("/role")
    public String interview(){
        return "/WEB-INF/views/role";
    }
}
