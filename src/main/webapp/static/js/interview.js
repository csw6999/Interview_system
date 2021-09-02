$(function () {

    /*数据表格展示*/
    $('#dg').datagrid({
        url:'/interviewList',
        columns:[[
            {field:'date',title:'日期',width:150,align:'center'},
            {field:'name',title:'姓名',width:150,align:'center'},
            {field:'age',title:'年龄',width:150,align:'center'},
            {field:'phone',title:'联系电话',width:150,align:'center'},
            {field:'school',title:'毕业院校',width:150,align:'center'},
            {field:'professional',title:'毕业专业',width:150,align:'center'},
            {field:'graduation_date',title:'毕业日期',width:150,align:'center'},
            {field:'salary',title:'期望薪资',width:150,align:'center'},
            {field:'interview_date',title:'面试日期',width:150,align:'center'},
            {field:'working_date',title:'到岗日期',width:150,align:'center'},
            {field:'second_interview',title:'有无复试',width:150,align:'center',formatter: function(value,row,index){
                    if(row.second_interview){
                        return "有";
                    }else {
                        return "无";
                    }
                }
            },
            {field:'interview_result',title:'面试结果',width:150,align:'center'},
            {field:'state',title:'工作状态',width:150,align:'center',formatter: function (value,row,index){
                    if(value.name){
                        return value.name;
                    }
                }},
            {field:'note',title:'备注',width:100,align:'center'},
        ]],
        fit:true,
        fitColumns:true,
        rownumbers:true,
        singleSelect:true,
        toolbar: '#tb',
        pagination:true,
        striped:true
    });

    /*工作状态下拉列表*/
    $('#state').combobox({
        url:'stateList',
        valueField:'id',
        textField:'name',
        editable:false,
        width:150,
        panelHeight:'auto',
        onLoadSuccess: function (){
            $("#state").each(function(i){
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if(targetInput){
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });

    /*设置默认选中*/
    /*$("#department").combobox("select","财务部");*/

    /*有无复试下拉列表*/
    $("#second_interview").combobox({
        width:150,
        panelHeight:'auto',
        valueField:'value',
        textField:'label',
        data: [{
            label: '有',
            value: 'true'
        },{
            label: '无',
            value: 'false'
        }],
        onLoadSuccess: function (){
            $("#second_interview").each(function(i){
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if(targetInput){
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    })

    /*设置默认选中*/
    /*$("#state").combobox("select","在职");*/

    /*对话框 */
    $("#dialog").dialog({
        width:500,
        height:500,
        buttons:[{
            text:'添加',
            iconCls:'icon-save',
            handler:function(){
                //区分提交地址url
                var id = $("[name='id']").val();
                var url;
                if (id){
                    url = "updateInterviewer";
                } else {
                    url= "saveInterviewer";
                }
                /*提交表单*/
                $("#myform").form("submit",{
                    url: url,
                    success:function (data) {
                        console.log(data);
                        /*解析成json*/
                        data = $.parseJSON(data);
                        if(data.success){
                            $.messager.alert("温馨提示",data.msg);
                            /*关闭对话框*/
                            $("#dialog").dialog("close");
                            /*重新加载数据表格*/
                            $("#dg").datagrid("reload");
                        }else {
                            $.messager.alert("温馨提示",data.msg);
                        }
                    }
                });
            }
        },{
            text:'关闭',
            iconCls:'icon-cancel',
            handler:function(){
                $("#dialog").dialog("close");
            }
        }],
        closed:true
    });

    /*添加*/
    $("#add").click(function () {

        $("#dialog").dialog({
            title: "添加面试信息"
        });
        /*清空表单*/
        $("#myform").form("clear");
        $("#dialog").dialog("open");
    });

    /*编辑*/
    $("#edit").click(function () {
        /*判断是否选中了数据*/
        var rowData = $("#dg").datagrid("getSelected");
        if(!rowData){
            $.messager.alert("温馨提示","请选中一条数据进行编辑");
            return;
        }
        $("#dialog").dialog("setTitle","编辑面试信息");
        /*{name: "王五", date: "2020-10-01", email: "wangwu@126.com", department: {…}, state: false}*/
        /*弹出对话框 */
        $("#dialog").dialog("open");
        console.log(rowData);
        /*数据回显  同名匹配*/
        rowData["state.id"]= rowData["state"].id;
        rowData["second_interview"] = rowData["second_interview"]+"";
        $("#myform").form("load",rowData);
    });

    /*删除*/
    $("#delete").click(function (){
        /*判断是否选中了数据*/
        var rowData = $("#dg").datagrid("getSelected");
        if(!rowData){
            $.messager.alert("温馨提示","请选中一条数据进行删除");
            return;
        }
        $.messager.confirm("确认","确认删除数据",function (res){
            if (res){
                $.get("/deleteInterviewer?id=" + rowData.id,function (data){
                    if(data.success){
                        $.messager.alert("温馨提示",data.msg);
                        /*关闭对话框*/
                        $("#dialog").dialog("close");
                        /*重新加载数据表格*/
                        $("#dg").datagrid("reload");
                    }else {
                        $.messager.alert("温馨提示",data.msg);
                    }
                });
            }
        });
    });

    /*查询*/
    $("#search").click(function (){
        /*获取搜索的内容*/
        var keyword = $("[name='keyword']").val();
        /*重新加载列表 把参数keyword传过去*/
        $("#dg").datagrid("load",{keyword:keyword});
    });

    /*刷新*/
    $("#reload").click(function (){
        var keyword = $("[name='keyword']").val('');
        $("#dg").datagrid("load",{});
    });

    /*导出*/
    $("#downloadExcel").click(function (){
        $.messager.confirm("确认","将面试信息导出到Excel",function (res){
            if (res){
                window.open('/downloadExcel')
            }
        })
    });

    /*导入对话框*/
    $("#excelUpload").dialog({
        width:260,
        height:180,
        title:"导入Excel",
        buttons:[{
            text:'上传',
            handler:function(){
                $("#uploadForm").form("submit",{
                   url: 'uploadExcel',
                   success:function (data) {
                        if(data.success){
                            $.messager.alert("温馨提示",data.msg);
                            /*关闭对话框*/
                            $("#dialog").dialog("close");
                            /*重新加载数据表格*/
                            $("#dg").datagrid("reload");
                        }else {
                            $.messager.alert("温馨提示",data.msg);
                        }
                    }
                });
            }
        },{
            text:'关闭',
            handler:function(){
                $("#excelUpload").dialog("close");
            }
        }],
        closed:true
    })
    /*导入*/
    $("#uploadExcel").click(function () {
        $("#excelUpload").dialog("open");
    });
});