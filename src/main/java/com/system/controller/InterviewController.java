package com.system.controller;

import com.system.entity.AjaxRes;
import com.system.entity.Interviewer;
import com.system.entity.PageListRes;
import com.system.entity.QueryVo;
import com.system.mapper.InterviewerMapper;
import com.system.service.InterviewService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
            queryVo.setRows(1000);
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
    @PostMapping("/uploadExcel")
    @ResponseBody
    public AjaxRes upLoadXlsx(@RequestParam("file") MultipartFile file) throws IOException, ParseException {
        //文件名
        String filename=file.getOriginalFilename();
        //创建一个文旦对象
        Workbook workbook=null;
        if(filename.endsWith("xlsx")){//Office 2007版本后excel.xls文件
            workbook=new XSSFWorkbook(file.getInputStream());
        }else if(filename.endsWith("xls")){//Office 2007版本前excel.xls文件
            workbook=new HSSFWorkbook(file.getInputStream());
        }else{
            throw new RuntimeException("不是Excel文件");
        }
        //獲取Sheet(表格業媚)
        Sheet sheet=workbook.getSheetAt(0);
        //獲取Row的數目
        System.out.println("rows:_----------------------------"+sheet.getLastRowNum());
        int rowNum=sheet.getLastRowNum();
        if(rowNum==0){//行不能為空
            throw new RuntimeException("沒有數據");
        }
        //遍歷所有的Row
        for(int i=1;i<=rowNum;i++){
            Row row=sheet.getRow(i);
            if(row!=null){//行不為空則讀取數據
                Interviewer interviewer=new Interviewer();
                //讀取Cell的值
                String date=row.getCell(1).getStringCellValue();
                String name=row.getCell(2).getStringCellValue();
                Cell ageCell = row.getCell(3);
                ageCell.setCellType(Cell.CELL_TYPE_STRING);
                Long age = Long.parseLong(ageCell.getStringCellValue());
                String phone=row.getCell(4).getStringCellValue();
                String school=row.getCell(5).getStringCellValue();
                String professional=row.getCell(6).getStringCellValue();
                Cell graduation_dateCell = row.getCell(7);
                graduation_dateCell.setCellType(Cell.CELL_TYPE_STRING);
                String graduation_dateValue = graduation_dateCell.getStringCellValue();
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                Date graduation_date = sf.parse(graduation_dateValue);
                Cell salaryCell = row.getCell(8);
                salaryCell.setCellType(Cell.CELL_TYPE_STRING);
                String salaryValue = salaryCell.getStringCellValue();
                BigDecimal salary = new BigDecimal(salaryValue);
                Cell interview_dateCell = row.getCell(9);
                interview_dateCell.setCellType(Cell.CELL_TYPE_STRING);
                String interview_dateValue = interview_dateCell.getStringCellValue();
                SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
                Date interview_date = sf1.parse(interview_dateValue);
                Cell working_dateCell = row.getCell(10);
                working_dateCell.setCellType(Cell.CELL_TYPE_STRING);
                String working_dateValue = working_dateCell.getStringCellValue();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date working_date = simpleDateFormat.parse(working_dateValue);
                Cell second_interviewCell = row.getCell(11);
                second_interviewCell.setCellType(Cell.CELL_TYPE_STRING);
                String second_interview = second_interviewCell.getStringCellValue();
                String interview_result=row.getCell(12).getStringCellValue();
                Cell stateCell = row.getCell(13);
                stateCell.setCellType(Cell.CELL_TYPE_STRING);
                String state = stateCell.getStringCellValue();
                String note=row.getCell(14).getStringCellValue();
                interviewer.setDate(date);
                interviewer.setName(name);
                interviewer.setAge(age);
                interviewer.setPhone(phone);
                interviewer.setSchool(school);
                interviewer.setProfessional(professional);
                interviewer.setGraduation_date(graduation_date);
                interviewer.setSalary(salary);
                interviewer.setInterview_date(interview_date);
                interviewer.setWorking_date(working_date);
                interviewer.setNote(note);
                interviewer.setInterview_result(interview_result);
                interviewer.setSecond_interview("有".equals(second_interview));
                if ("在职".equals(state)){
                    interviewer.setState_id(1);
                } else if ("离职".equals(state)){
                    interviewer.setState_id(2);
                } else if ("应届生".equals(state)){
                    interviewer.setState_id(3);
                } else if ("往届生".equals(state)){
                    interviewer.setState_id(4);
                }
                interviewService.add(interviewer);
            }
        }
        log.info("成功导入");
        AjaxRes ajaxRes = new AjaxRes();
            ajaxRes.setMsg("文件导入成功");
            ajaxRes.setSuccess(true);
        return ajaxRes;
    }
}


