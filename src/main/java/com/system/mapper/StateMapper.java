package com.system.mapper;

import com.system.entity.State;
import java.util.List;

public interface StateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(State record);

    State selectByPrimaryKey(Long id);

    List<State> selectAll();

    int updateByPrimaryKey(State record);
}