package com.system.controller;

import com.system.entity.State;
import com.system.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StateController {
    @Autowired
    StateService stateService;

    @RequestMapping("/stateList")
    @ResponseBody
    public List<State> stateList() {
        return stateService.getStateList();
    }
}
