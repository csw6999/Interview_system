package com.system.controller;

import com.system.entity.AjaxRes;
import com.system.entity.Interviewer;
import com.system.entity.PageListRes;
import com.system.entity.QueryVo;
import com.system.mapper.InterviewerMapper;
import com.system.service.InterviewService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@Slf4j
public class InterviewController {

    @Autowired
    InterviewService interviewService;
    @Autowired
    InterviewerMapper interviewerMapper;

    @RequestMapping("/interview")
    public String interview() {
        return "/WEB-INF/views/interview";
    }

    /*查询面试信息*/
    @RequestMapping("/interviewList")
    @ResponseBody
    public PageListRes interviewList(QueryVo vo) {
        /*调用业务层查询面试信息*/
        log.info("前端");
        return interviewService.getInterview(vo);
    }

    /*添加面试信息*/
    @RequestMapping("/saveInterviewer")
    @ResponseBody
    public AjaxRes saveInterviewer(Interviewer interviewer) {
        log.info("提交表单成功！！！");
        //System.out.println(interviewer);
        AjaxRes ajaxRes = new AjaxRes();
        try {
            interviewService.saveInterviewer(interviewer);
            ajaxRes.setMsg("保存成功");
            ajaxRes.setSuccess(true);
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("保存失败");
        }
        return ajaxRes;
    }

    /*更新面试信息*/
    @RequestMapping("/updateInterviewer")
    @ResponseBody
    public AjaxRes updateInterviewer(Interviewer interviewer) {
        log.info("更新表单成功！！！");
        //System.out.println(interviewer);
        AjaxRes ajaxRes = new AjaxRes();
        try {
            interviewService.updateInterviewer(interviewer);
            ajaxRes.setMsg("更新成功");
            ajaxRes.setSuccess(true);
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("更新失败");
        }
        return ajaxRes;
    }

    /*删除面试信息*/
    @RequestMapping("/deleteInterviewer")
    @ResponseBody
    public AjaxRes deleteInterviewer(Long id) {
        log.info("成功删除数据");
        AjaxRes ajaxRes = new AjaxRes();
        try {
            interviewService.deleteInterviewer(id);
            ajaxRes.setMsg("删除成功");
            ajaxRes.setSuccess(true);
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("删除失败");
        }
        return ajaxRes;
    }

    /*导出面试信息到Excel*/
    @RequestMapping("/downloadExcel")
    @ResponseBody
    public void downloadExcel(HttpServletResponse response) {
        log.info("即将导出面试信息");
        try {
            String filename = new String("面试信息.xlsx".getBytes(StandardCharsets.UTF_8), "iso8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            QueryVo queryVo = new QueryVo();
            queryVo.setPage(1);
            queryVo.setRows(100);
            PageListRes interview = interviewService.getInterview(queryVo);
            List<Interviewer> interviewers = (List<Interviewer>) interview.getRows();
            XSSFWorkbook sheets = new XSSFWorkbook();
            XSSFCellStyle cellStyle = sheets.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            XSSFSheet sheet = sheets.createSheet("面试信息");
            XSSFRow row = sheet.createRow(0);
            sheet.setDefaultColumnWidth(11);
            row.createCell(0).getCellStyle().setAlignment(HorizontalAlignment.CENTER);//水平居中
            row.createCell(0).getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
            row.createCell(0).setCellValue("编号");
            row.createCell(1).setCellValue("日期");
            row.createCell(2).setCellValue("姓名");
            row.createCell(3).setCellValue("年龄");
            row.createCell(4).setCellValue("联系电话");
            row.createCell(5).setCellValue("毕业院校");
            row.createCell(6).setCellValue("毕业专业");
            row.createCell(7).setCellValue("毕业日期");
            row.createCell(8).setCellValue("期望薪资");
            row.createCell(9).setCellValue("面试日期");
            row.createCell(10).setCellValue("到岗日期");
            row.createCell(11).setCellValue("有无复试");
            row.createCell(12).setCellValue("面试结果");
            row.createCell(13).setCellValue("工作状态");
            row.createCell(14).setCellValue("备注");
            XSSFRow interviewerRow = null;
            for (int i = 0; i < interviewers.size(); i++) {
                Interviewer interviewer = interviewers.get(i);
                interviewerRow = sheet.createRow(i + 1);
                interviewerRow.setRowStyle(cellStyle);
                interviewerRow.createCell(0).setCellValue(i);
                interviewerRow.createCell(1).setCellValue(interviewer.getDate());
                interviewerRow.createCell(2).setCellValue(interviewer.getName());
                interviewerRow.createCell(3).setCellValue(interviewer.getAge());
                interviewerRow.createCell(4).setCellValue(interviewer.getPhone());
                interviewerRow.createCell(5).setCellValue(interviewer.getSchool());
                interviewerRow.createCell(6).setCellValue(interviewer.getProfessional());
                if (interviewer.getGraduation_date() != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String format = sdf.format(interviewer.getGraduation_date());
                    interviewerRow.createCell(7).setCellValue(format);
                } else {
                    interviewerRow.createCell(7).setCellValue(" ");
                }
                interviewerRow.createCell(8).setCellValue(String.valueOf(interviewer.getSalary()));
                if (interviewer.getGraduation_date() != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String format = sdf.format(interviewer.getInterview_date());
                    interviewerRow.createCell(9).setCellValue(format);
                } else {
                    interviewerRow.createCell(9).setCellValue(" ");
                }
                if (interviewer.getGraduation_date() != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String format = sdf.format(interviewer.getWorking_date());
                    interviewerRow.createCell(10).setCellValue(format);
                } else {
                    interviewerRow.createCell(10).setCellValue(" ");
                }
                if (interviewer.getSecond_interview().equals(true)) {
                    interviewerRow.createCell(11).setCellValue("有");
                } else if (interviewer.getSecond_interview().equals(false)) {
                    interviewerRow.createCell(11).setCellValue("无");
                }
                interviewerRow.createCell(12).setCellValue(interviewer.getInterview_result());
                switch (interviewer.getState().toString()) {
                    case "State(id=1, name=在职)":
                        interviewerRow.createCell(13).setCellValue("在职");
                        break;
                    case "State(id=2, name=离职)":
                        interviewerRow.createCell(13).setCellValue("离职");
                        break;
                    case "State(id=3, name=应届生)":
                        interviewerRow.createCell(13).setCellValue("应届生");
                        break;
                    case "State(id=4, name=往届生)":
                        interviewerRow.createCell(13).setCellValue("往届生");
                        break;
                }
                interviewerRow.createCell(14).setCellValue(interviewer.getNote());
            }
            sheets.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*从Excel导入面试信息*/
    @RequestMapping("/uploadExcel")
    @ResponseBody
    public void uploadExcel() {
        log.info("即将导入面试信息");
        log.info("HHHH");
        XSSFWorkbook xssfSheets = new XSSFWorkbook();
        XSSFSheet sheetAt = xssfSheets.getSheetAt(0);
        int lastRowNum = sheetAt.getLastRowNum();
        for (int i = 0; i < lastRowNum; i++) {
            XSSFRow row = sheetAt.getRow(i);
            log.info(String.valueOf(row));

        }
    }
}


