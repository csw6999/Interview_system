package com.system.service;

import com.system.entity.Interviewer;
import com.system.entity.PageListRes;
import com.system.entity.QueryVo;

public interface InterviewService {

    PageListRes getInterview(QueryVo vo);

    void saveInterviewer(Interviewer interviewer);

    void add(Interviewer interviewer);

    void updateInterviewer(Interviewer interviewer);

    void deleteInterviewer(Long id);

}
