package com.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeController {
    @RequestMapping("/employee")
    public String interview(){
        return "/WEB-INF/views/employee";
    }
}
