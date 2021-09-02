<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>面试管理系统</title>
    <%@include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/index.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height:100px; background: #0099FF; padding: 20px 20px">
    <img src="static/images/main_logo.png" alt="">
</div>
<div data-options="region:'south'" style="height:50px; border-bottom: 3px solid #0099FF">
    <p align="center" style="font-size: 14px">面试管理系统</p>
</div>
<div data-options="region:'west',split:true" style="width:200px;">

    <div id="aa" class="easyui-accordion" data-options="fit:true">
        <div title="菜单" data-options="iconCls:'icon-more',selected:true" style="overflow:auto;padding:10px;">
            <!--tree-->
            <ul id="tree"></ul>
        </div>
        <div title="Title2" data-options="iconCls:'icon-more'" style="padding:10px;">
            待开发
        </div>
        <div title="Title3" data-options="iconCls:'icon-more'" style="padding:10px;">
            待开发
        </div>
    </div>
</div>
<div data-options="region:'center'" style="background:#eee;">
    <!--标签-->
    <div id="tabs" style="overflow: hidden" >
    </div>
</div>

</body>
</html>
