<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/interview.js"></script>
</head>
<body>
<%--数据表格--%>
<table id="dg"></table>
<%--工具栏--%>
<div id="tb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="add">添加</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" id="edit">编辑</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="delete">删除</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" id="reload">刷新</a>
    <input type="text" name="keyword" style="width: 200px; height: 27px;padding-left: 5px;">
    <a class="easyui-linkbutton" iconCls="icon-search" id="search">查询</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print',plain:true" style="float: right;margin-top: 10px" id="downloadExcel" >导出</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" style="float: right;margin-top: 10px" id="uploadExcel">导入</a>
</div>
<%--对话框--%>
<div id="dialog" >
    <form id="myform">
        <%--添加一个隐藏域  编辑--%>
        <input type="hidden" name="id">
        <table align="center" style="border-spacing: 2px 15px">

            <tr>
                <td>日期:</td>
                <td><input type="text" name="date" class="easyui-datebox" required="required"></td>
            </tr>
            <tr>
                <td>姓名:</td>
                <td><input type="text" name="name" class="easyui-validatebox" data-options="required:true"></td>
            </tr>
            <tr>
                <td>年龄:</td>
                <td><input type="text" name="age" class="easyui-validatebox" data-options="required:true"></td>
            </tr>
            <tr>
                <td>联系电话:</td>
                <td><input type="text" name="phone" class="easyui-validatebox" data-options="required:true"></td>
            </tr>
            <tr>
                <td>毕业院校:</td>
                <td><input type="text" name="school" class="easyui-validatebox"></td>
            </tr>
            <tr>
                <td>毕业专业:</td>
                <td><input type="text" name="professional" class="easyui-validatebox"></td>
            </tr>
            <tr>
                <td>毕业日期:</td>
                <td><input type="text" name="graduation_date" class="easyui-datebox"></td>
            </tr>
            <tr>
                <td>期望薪资:</td>
                <td><input type="text" name="salary" class="easyui-validatebox"></td>
            </tr>
            <tr>
                <td>面试日期:</td>
                <td><input type="text" name="interview_date" class="easyui-datebox"></td>
            </tr>
            <tr>
                <td>到岗日期:</td>
                <td><input type="text" name="working_date" class="easyui-datebox"></td>
            </tr>
            <tr>
                <td>有无复试:</td>
                <td><input id="second_interview" name="second_interview" placeholder="是否有复试"/></td>
            </tr>
            <tr>
                <td>面试结果:</td>
                <td><input type="text" name="interview_result" class="easyui-validatebox"></td>
            </tr>
            <tr>
                <td>工作状态:</td>
                <td><input id="state" name="state.id" placeholder="请选择工作状态"/></td>
            </tr>
            <tr>
                <td>备注:</td>
                <td><input type="text" name="note" class="easyui-validatebox"></td>
            </tr>
        </table>
    </form>
</div>
<%--上传界面--%>
<div id="excelUpload">
    <form method="post" enctype="multipart/form-data" id="uploadForm">
        <tabel>
            <tr>
                <td><input type="file" name="excel" style="width: 180px; margin-top: 20px; margin-left: 5px;"></td>
            </tr>
        </tabel>
    </form>
</div>
</body>
</html>
