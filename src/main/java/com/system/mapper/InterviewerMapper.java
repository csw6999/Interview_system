package com.system.mapper;

import com.system.entity.Interviewer;
import com.system.entity.QueryVo;

import java.util.List;

public interface InterviewerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Interviewer record);

    void add(Interviewer interviewer);

    Interviewer selectByPrimaryKey(Long id);

    List<Interviewer> selectAll(QueryVo vo);

    int updateByPrimaryKey(Interviewer record);

}