<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>sjgjappdata-add</title>
<!-- <script type="text/javascript">

	// 附件上传
	function uploadcsv(){
		// 通过jquery.form.js实现form表单提交
		var options = {
				url:"/sjgjsend/uploadcsv.action",
				type:"post",
				dataType:"json",
				success:function(data){
					// 1、图片回显；2、将图片的路径保存到隐藏域中
					$("#csvUrl").val(data.csvUrl); // 设置隐藏域的值
					alert(data.msg);
				}
		}
		
		$("#jvForm").ajaxSubmit(options);
	}
	
</script> -->
<!-- <script type="text/javascript">
function querybname(){
	 $("select[name='bname']").empty();
	var param=$("#typename").val();
	/*  alert("被选项目的值："+param); */
	 $.get(
		"/sjgjapp/book/querybname.action?typename="+param,
		   function(data){
			    $("select[name='bname']").append('<option value="">请选择图书</option>');
			    var date = eval(data);
			    for(var i=0;i<date.length;i++){
			    	var obj = date[i].bname;
			    	$("select[name='bname']").append('<option value="'+obj+'">'+obj+'</option>'); 
			    
			     } 
			 },
			 "json")
}
	
</script> -->
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: App数据- 导入</div>
	<!-- <form class="ropt">
		<input type="submit" onclick="this.form.action='/knowledge/list.action';" value="返回列表" class="return-button"/>
	</form> -->
	<div class="clear"></div>
</div>
<div class="body-box" style="float:right">
	<form id="jvForm" action="/sjgjsend/data/appdatainsert.action" method="post" enctype="multipart/form-data">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody>
			<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
					<span class="pn-frequired">*</span>
						上次导入时间:</td>
						<td width="80%" class="pn-fcontent">
						${fdatatime}
					</td>
				</tr>
			
			</tbody>
			<tbody>
				<tr>
					<td class="pn-fbutton" colspan="2">
						<input type="submit" class="submit" value="导入"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>