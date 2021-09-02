package com.system.service;

import com.system.entity.Interviewer;
import com.system.entity.PageListRes;
import com.system.entity.QueryVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InterviewService {

    PageListRes getInterview(QueryVo vo);

    void saveInterviewer(Interviewer interviewer);

    void updateInterviewer(Interviewer interviewer);

    void deleteInterviewer(Long id);

}
