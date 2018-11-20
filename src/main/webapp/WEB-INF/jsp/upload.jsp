<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<script type="text/javascript" src="<%=basePath%>static/jquery.min.js"></script>
<title></title>
</head>
<body>
	<form id="uploadForm" enctype="multipart/form-data">
		文件:<input id="file" type="file" name="file" />
	</form>
	<button id="upload">上传文件</button>
</body>
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