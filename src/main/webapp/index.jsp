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
	<div data-options="region:'south',split:true" style="height:30px;">
		云耳科技有限公司 版权所有 系统维护 Marc 2018 <font color="gray">最后更新日期2018/11/21:::....</font>
	</div>
	<div data-options="region:'west',collapsible:false,split:true"
		style="width:200px;"></div>

	<!-- 中部layout, 放置datagrid -->
	<div data-options="region:'center'"
		style="padding:5px;background:#eee;">
		<!-- 工具栏 -->
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add"
				id="tool_bar_add_btn">新增版本记录</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-edit" id="tool_bar_edit_btn">编辑版本记录</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
				id="tool_bar_remove_btn">删除一条记录</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-reload"
				id="tool_bar_reload_btn">刷新</a>
		</div>
		<table class="easyui-datagrid" id="fv_datagrid"
			data-options="singleSelect:true,collapsible:true,url:'fv/listAll.do',method:'get'">
			<thead>
				<tr>
					<th data-options="field:'file_NAME',width:350,align:'center'">文件名</th>
					<th data-options="field:'file_MD5',width:350,align:'center'">文件MD5编码</th>
					<th data-options="field:'file_SIZE',width:80,align:'center'">文件尺寸</th>
					<th data-options="field:'hardware_MODEL',width:150,align:'center'">硬件型号</th>
					<th data-options="field:'id',width:350,align:'center'">ID</th>
					<th data-options="field:'version',width:150,align:'center'">版本号</th>
					<th data-options="field:'client_NAME',width:80,align:'center'">客户</th>
					<th data-options="field:'sn',width:150,align:'center'">序列号</th>
					<th data-options="field:'description',width:150,align:'center'">补充说明</th>
					<th
						data-options="field:'create_TIME',width:200,align:'center',formatter:function(value,row,index){  
                         var unixTimestamp = new Date(value); return unixTimestamp.toLocaleString();  
                         } ">记录创建日期</th>
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
			<input type="text" id="id_input" name="ID" class="easyui-textbox">
			<table>
				<tr>
					<td>客户</td>
					<td><input type="text" id="client_name_input"
						name="CLIENT_NAME" style="width: 150px" class="easyui-textbox">
						&nbsp;</td>
				</tr>
				<tr>
					<td>硬件型号</td>
					<td><input type="text" id="hardware_model_input"
						name="HARDWARE_MODEL" style="width: 150px" class="easyui-textbox">
						&nbsp;</td>
				</tr>
				<tr>
					<td>版本号</td>
					<td><input type="text" id="version_input" name="VERSION"
						style="width: 150px" class="easyui-textbox"> &nbsp;</td>
				</tr>
				<tr>
					<td>固件描述</td>
					<td><input type="text" id="description_input"
						name="DESCRIPTION" class="easyui-textbox" style="width: 350px">
						&nbsp;</td>
				</tr>
				<tr>
					<td>序列号</td>
					<td><input type="text" id="sn_input" name="SN"
						class="easyui-textbox" style="width: 150px"> &nbsp;</td>
				</tr>
				<tr>
					<td>文件尺寸</td>
					<td><input type="text" id="file_size_input" name="FILE_SIZE"
						class="easyui-textbox" style="width: 150px">
						&nbsp;Bytes/字节</td>
				</tr>
				<tr>
					<td>文件名以及路径</td>
					<td><input type="text" id="file_name_input" name="FILE_NAME"
						style="width: 350px" class="easyui-textbox"> &nbsp;</td>
				</tr>
				<tr>
					<td>文件MD5校验码</td>
					<td><input type="text" id="file_md5_input" name="FILE_MD5"
						style="width: 350px" class="easyui-textbox"> &nbsp;</td>
				</tr>

			</table>
		</form>

		<!-- 文件上传的部分 -->
		<div id="upload_div_part" class="upload"
			style="padding:10px 20px 10px 40px;">

			<form id="uploadForm" enctype="multipart/form-data">
				<input id="file" type="file" name="file" />
			</form>
			<button id="upload">上传文件</button>

		</div>
		<div style="padding:10px 20px 10px 40px;">
			<a id="save_panel_comfirm_btn" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'">确定</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="save_panel_cancel_btn"
				href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>

		</div>
	</div>

	<!-- 登陆遮罩window -->
	<div id="login_win" class="easyui-window" title="登陆"
		style="width:300px;height:250px;"
		data-options="modal:true,collapsible:false,minimizable:false,maximizable:false,closable:false">
		<form style="padding:10px 20px 10px 40px;" id="login_form">
			<p>
				用户名: <br> <input type="text" name="LOGIN_NAME">
			</p>
			<p>
				密码: <br> <input type="password" name="PASSWORD">
			</p>
			<div style="padding:5px;text-align:center;">
				<a href="#" class="easyui-linkbutton" icon="icon-ok" id="login_btn">OK</a>
			</div>
		</form>
	</div>


</body>
<script type="text/javascript">

	var submitUrl = '';

	//启动用初始化脚本 
	$(function() {
		//测试脚本启动及jQuery引用
		console.info('scrtip start..');

		//id_input在add_update panel需要隐藏
		$('#id_input').next().hide();

		//将panel隐藏一下
		$('#add_panel').window('close');

		//此处将window拉到中间位置.
		$('#login_win').window({
			top : 100
		});

		//定义login_form的url, 返回数据处理
		$('#login_form').form({
			url : 'sys/findSysUserByLoginNamePassword.do',
			onSubmit : function() {},
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
				}
			}
		});

		$('#add_edit_form').form({
			success : function(data) {
				//change the JSON string to javascript object
				//console.info(data);
				var data = eval('(' + data + ')');
				//console.info(data);
				if (data.success) {
					$('#add_panel').window('close');
					$.messager.show({
						title : '成功',
						msg : '数据保存成功',
						timeout : 1000,
						showType : 'slide'
					});
					$('#fv_datagrid').datagrid('reload');
					$('#uploadForm').form('clear');
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

		//编辑按钮绑定事件
		$('#tool_bar_edit_btn').bind('click', function() {
			//获取选择到的数据, 后面要拿这行数据填充要修改的数据form
			var row = $('#fv_datagrid').datagrid("getSelected");
			console.info(row);
			if (row) {
				//var url = 'fv/updateFVByID.do';

				//隐藏文件上传的部分div
				$('#upload_div_part').hide();

				//打开add_update panel
				$('#add_panel').window("setTitle", "修改记录(已经上传的文件的信息不可以直接修改, 有必要可以删除记录后重新上传)");
				$('#add_panel').window('open');

				//修改form的action
				$('#add_edit_form').attr('action', "fv/updateFVByID.do");

				//submitUrl = 'fv/updateFVByID.do';
				//console.info(submitUrl);

				//逐一填充数据
				$('#client_name_input').textbox('setValue', row.client_NAME);
				$('#hardware_model_input').textbox('setValue', row.hardware_MODEL);

				$('#version_input').textbox('setValue', row.version);
				$('#sn_input').textbox('setValue', row.sn);
				$('#id_input').textbox('setValue', row.id);

				$('#description_input').textbox('setValue', row.description);

				$('#file_size_input').textbox('readonly', true);
				$('#file_size_input').textbox('setValue', row.file_SIZE + '  (不可更改)');

				$('#file_name_input').textbox('readonly', true);
				$('#file_name_input').textbox('setValue', row.file_NAME + '  (不可更改)');

				$('#file_md5_input').textbox('readonly', true);
				$('#file_md5_input').textbox('setValue', row.file_MD5 + '  (不可更改)');

			//});
			}
		});

		//删除按钮的绑定事件
		$('#tool_bar_remove_btn').bind('click', function() {
			//console.info('remove_btn');
			//获取选择的行
			var row = $('#fv_datagrid').datagrid("getSelected");
			//console.info(row);
			if (row) {
				//将url指向删除接口removeFVByID.do
				var url = 'fv/removeFVByID.do';
				//需要用户对删除做确定,避免错误操作
				$.messager.confirm("确认", "确定要删除该条设备信息吗？", function(r) {
					if (r) {
						//用户确认之后, 就post到删除接口去
						$.post(
							url, {
								ID : row.id
							}, function(result) {
								if (result.success) {
									$.messager.show({
										title : "操作成功",
										timeout : 1000,
										msg : result.msg
									});
								} else {
									$.messager.show({
										title : "错误",
										timeout : 1000,
										msg : result.msg
									});
								}
								$('#fv_datagrid').datagrid('reload');
							}, "json");
					}
				});
			}
		});

		//新增记录按钮
		$('#tool_bar_add_btn').bind('click', function() {
			//设置title
			$('#add_panel').window("setTitle", "新增记录");

			//打开对话框
			$('#add_panel').window('open');

			//显示文件上传的部分div
			$('#upload_div_part').show();

			//将原有的表单内容清除
			$('#add_edit_form').form('clear');

			//修改表单的提交地址为新增用接口addFV.do
			$('#add_edit_form').attr('action', "fv/addFV.do");
		//submitUrl = 'fv/addFV.do';
		});

		//确定提交表单按钮
		$('#save_panel_comfirm_btn').bind('click', function() {
			console.info('save_panel_comfirm_btn clicked');
			if ($('#file_name_input').textbox('getValue') == '') {
				$.messager.show({
					title : '提示',
					msg : '请先上传文件',
					timeout : 1000,
					showType : 'slide'
				});
			} else {
				$('#add_edit_form').submit();
			}
		});

		//取消表单按钮
		$('#save_panel_cancel_btn').bind('click', function() {
			console.info('save_panel_cancel_btn clicked');
			$('#add_panel').window('close');
		});

		$('#tool_bar_reload_btn').bind('click', function() {
			$('#fv_datagrid').datagrid('reload');
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
					//console.info('yeah!');
					$.messager.show({
						title : '成功',
						msg : data.msg + ' 上传成功',
						timeout : 1000,
						showType : 'slide'
					});

					//设置需要填充的内容
					$('#file_size_input').textbox('readonly', true);
					$('#file_size_input').textbox('setValue', data.obj.fileSize);

					$('#file_name_input').textbox('readonly', true);
					$('#file_name_input').textbox('setValue', data.obj.filePath);

					$('#file_md5_input').textbox('readonly', true);
					$('#file_md5_input').textbox('setValue', data.obj.fileMD5);


				} else {
					//console.info('false');
					$.messager.show({
						title : '错误',
						msg : data.msg,
						timeout : 1000,
						showType : 'slide'
					});
				}
			}).error(function() {
				$.messager.show({
					title : '错误',
					msg : data.msg + '   上传失败',
					timeout : 1000,
					showType : 'slide'
				});
			});
		});
	});
</script>
</html>
