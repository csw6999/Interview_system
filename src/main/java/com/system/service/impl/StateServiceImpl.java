package com.system.service.impl;

import com.system.entity.State;
import com.system.mapper.StateMapper;
import com.system.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StateServiceImpl implements StateService {
    @Autowired
    StateMapper stateMapper;
    @Override
    public List<State> getStateList(){
        return  stateMapper.selectAll();
    }
}
