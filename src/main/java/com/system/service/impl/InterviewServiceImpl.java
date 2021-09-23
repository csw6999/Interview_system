package com.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.system.entity.Interviewer;
import com.system.entity.PageListRes;
import com.system.entity.QueryVo;
import com.system.mapper.InterviewerMapper;
import com.system.service.InterviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    InterviewerMapper interviewerMapper;

    @Override
    public PageListRes getInterview(QueryVo vo){
        /*调用Mapper 查询面试信息*/
        Page<Object> page = PageHelper.startPage(vo.getPage(), vo.getRows());
        List<Interviewer> interviewers = interviewerMapper.selectAll(vo);
        /*封装成PageList*/
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(interviewers);
        return pageListRes;
    }

    @Override
    public void saveInterviewer(Interviewer interviewer) {
        interviewerMapper.insert(interviewer);
    }

    @Override
    public void add(Interviewer interviewer) {
        interviewerMapper.add(interviewer);
    }

    @Override
    public void updateInterviewer(Interviewer interviewer) {
        interviewerMapper.updateByPrimaryKey(interviewer);
    }

    @Override
    public void deleteInterviewer(Long id) {
        interviewerMapper.deleteByPrimaryKey(id);
    }




}
