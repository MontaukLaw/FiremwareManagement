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
<script type="text/javascript" src="<%=basePath%>static/ajaxuploader.js"></script>
</head>
<body class="easyui-layout">
	<!-- 底层布局部分 -->
	<div data-options="region:'north',split:true" style="height:80px;">
		<div style="margin:10px 20px 15px">
			<h1>固件版本管理系统</h1>
		</div>
	</div>
	<!-- 南部layout -->
	<div data-options="region:'south',split:true" style="height:30px;">云耳科技有限公司
		版权所有 系统维护 Marc 2018</div>
	<div data-options="region:'west',collapsible:false,split:true"
		style="width:200px;"></div>

	<!-- 中部layout, 放置datagrid -->
	<div data-options="region:'center'"
		style="padding:5px;background:#eee;">
		<!-- 工具栏 -->
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
				id="tool_bar_add_btn"></a> <a href="#" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" id="tool_bar_add_btn"></a> <a
				href="#" class="easyui-linkbutton" iconCls="icon-remove"
				plain="true" id="tool_bar_remove_btn"></a>
		</div>
		<table class="easyui-datagrid" id="fv_datagrid"
			data-options="singleSelect:true,collapsible:true,url:'fv/listAll.do',method:'get'">
			<thead>
				<tr>
					<th data-options="field:'id',width:350,align:'center'">ID</th>
					<th data-options="field:'create_TIME',width:150,align:'center'">记录创建日期</th>
					<th data-options="field:'file_SIZE',width:80,align:'center'">文件尺寸</th>
					<th data-options="field:'version',width:150,align:'center'">版本号</th>
					<th data-options="field:'client_NAME',width:80,align:'center'">客户</th>
					<th data-options="field:'sn',width:150,align:'center'">序列号</th>
					<th data-options="field:'description',width:150,align:'center'">补充说明</th>
					<th data-options="field:'file_NAME',width:150,align:'center'">文件名</th>
					<th data-options="field:'file_MD5',width:150,align:'center'">文件MD5编码</th>
					<th data-options="field:'hardware_MODEL',width:150,align:'center'">硬件型号</th>
				</tr>
			</thead>
		</table>
	</div>

	<!-- 修改及新增panel -->
	<div id="add_panel" class="easyui-window"
		style="width:600px;height:500px;"
		data-options="collapsible:false,minimizable:false,maximizable:false">
		<form style="padding:10px 20px 10px 40px;" id="add_edit_form"
			method="post">
			<table>
				<tr>
					<td>客户</td>
					<td><input type="text" id="client_name_input"
						name="CLIENT_NAME" style="width: 150px"> &nbsp;</td>
				</tr>
				<tr>
					<td>硬件型号</td>
					<td><input type="text" id="hardware_model_input"
						name="HARDWRAE_MODEL" style="width: 150px"
						data-options="required:true"> &nbsp;</td>
				</tr>
				<tr>
					<td>固件描述</td>
					<td><textarea rows="3" cols="20" name="DESCRIPTION"></textarea></td>
				</tr>
				<tr>
					<td>序列号</td>
					<td><input type="text" id="sn_input" name="SN"
						style="width: 150px" data-options="required:true"> &nbsp;</td>
				</tr>

			</table>
		</form>

		<!-- 文件上传的部分 -->
		<div class="upload" style="padding:10px 20px 10px 40px;">
			-----------------------------</br>
			<form id="uploadForm" enctype="multipart/form-data">
				文件:<input id="file" type="file" name="file" />
			</form>
			<button id="upload">上传文件</button>

		</div>
	</div>

	<!-- 登陆遮罩window -->
	<div id="login_win" class="easyui-window" title="登陆"
		style="width:300px;height:250px;"
		data-options="iconCls:'icon-save',modal:true,collapsible:false,minimizable:false,maximizable:false,closable:false">
		<form style="padding:10px 20px 10px 40px;" id="login_form">
			<p>
				用户名: </br> <input type="text" name="LOGIN_NAME">
			</p>
			<p>
				密码: </br> <input type="password" name="PASSWORD">
			</p>
			<div style="padding:5px;text-align:center;">
				<a href="#" class="easyui-linkbutton" icon="icon-ok" id="login_btn">Ok</a>
			</div>
		</form>
	</div>


</body>
<script type="text/javascript">
	//启动用初始化脚本 
	$(function() {
		//测试脚本启动及jQuery引用
		console.info('scrtip start..');

		var submitUrl = '';

		//将panel隐藏一下
		$('#add_panel').window('close');

		//此处将window拉到中间位置.
		$('#login_win').window({
			top : 100
		});

		//定义login_form的url, 返回数据处理
		$('#login_form').form({
			url : 'sys/findSysUserByLoginNamePassword.do',
			onSubmit : function() {
				// do some check
				// return false to prevent submit;
			},
			success : function(data) {
				//change the JSON string to javascript object
				var data = eval('(' + data + ')');
				if (data.success) {
					$('#login_win').window('close');
				} else {
					$.messager.show({
						title : '错误',
						msg : data.msg,
						timeout : 1000,
						showType : 'slide'
					});
				//alert(data.msg);
				}
			}
		});

		//按键提交form
		$('#login_btn').bind('click', function() {
			//console.info('login_btn clicked');
			//因为未写好接口, 暂时先把这个窗口关掉
			//$('#login_win').window('close');

			$('#login_form').submit();

		})

		//删除按钮
		$('#tool_bar_remove_btn').bind('click', function() {
			//console.info('remove_btn');
			var row = $('#fv_datagrid').datagrid("getSelected");
			//console.info(row);
			if (row) {
				url = 'fv/removeByID.do';

			}
		});

		//新增记录按钮
		$('#tool_bar_add_btn').bind('click', function() {
			$('#add_panel').window("setTitle", "新增记录");
			$('#add_panel').window('open');
			$('#add_edit_form').form('clear');
			submitUrl = 'file/upload.do';
		});

		//确定上传按钮
		$('#file_upload_confirm_btn').bind('click', function() {
			//console.info('file_upload_confirm_btn clicked');
			$.ajax({
				type : "POST",
				url : submitUrl,
				data : data,
				success : function(result) {
					$.messager.show({
						title : "OK!",
						msg : result.obj
					})
				},
				dataType : dataType
			});
		});

	})
</script>

<!-- 独立出来的文件上传用ajax -->
<script type="text/javascript">
	$(function() {
		$("#upload").click(function() {
			var formData = new FormData($('#uploadForm')[0]);
			$.ajax({
				type : 'post',
				url : "file/upload.do",
				data : formData,
				cache : false,
				processData : false,
				contentType : false,
			}).success(function(data) {
				console.info(data);
				//var jdata = eval('(' + data + ')');
				if (data.success) {
					//$('#login_win').window('close');
					console.info('yeah!');
				} else {
					console.info('false');
				}
			}).error(function() {
				alert("上传失败");
			});
		});
	});
</script>
</html>
