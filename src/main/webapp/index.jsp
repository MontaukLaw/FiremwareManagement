<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>云耳固件管理后台 By Marc LAW zunly@hotmail.com</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/demo.css">
<script type="text/javascript" src="<%=basePath%>static/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>static/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
	<!-- 底层布局部分 -->
	<div data-options="region:'north',split:true" style="height:60px;">
		<div style="margin:10px 20px 15px">
			<h2>固件版本管理系统</h2>
		</div>

	</div>
	<div data-options="region:'south',split:true" style="height:30px;">云耳科技有限公司
		版权所有 系统维护 Marc 2018</div>
	<div data-options="region:'west',collapsible:false,split:true"
		style="width:200px;"></div>
	<div data-options="region:'center'"
		style="padding:5px;background:#eee;"></div>

	<!-- 登陆遮罩window -->
	<div id="login_win" class="easyui-window" title="登陆"
		style="width:300px;height:250px;"
		data-options="iconCls:'icon-save',modal:true,collapsible:false,minimizable:false,maximizable:false,closable:false">
		<form style="padding:10px 20px 10px 40px;">
			<p>
				用户名: </br> <input type="text">
			</p>
			<p>
				密码: </br> <input type="password">
			</p>
			<div style="padding:5px;text-align:center;">
				<a href="#" class="easyui-linkbutton" icon="icon-ok" id="login_btn">Ok</a>
			</div>
		</form>
	</div>


	<script type="text/javascript">
		//启动用初始化脚本 
		$(function() {
			//测试脚本启动及jQuery引用
			console.info('scrtip start..');
			//此处将window拉到中间位置.
			$('#login_win').window({
				top : 100
			});
	
			$('#login_btn').bind('click', function() {
				console.info('login_btn clicked');
				//因为未写好接口, 暂时先把这个窗口关掉
				$('#login_win').window('close');
			})
		})
	</script>
</body>
</html>
