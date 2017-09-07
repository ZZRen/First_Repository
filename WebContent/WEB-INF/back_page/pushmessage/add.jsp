<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>sjgj-add</title>
<style type="">
.h2_ch a:hover, .h2_ch a.here {
    color: #FFF;
    font-weight: bold;
    background-position: 0px -32px;
}
.h2_ch a {
    float: left;
    height: 32px;
    margin-right: -1px;
    padding: 0px 16px;
    line-height: 32px;
    font-size: 14px;
    font-weight: normal;
    border: 1px solid #C5C5C5;
    background: url('../res/itcast/img/admin/bg_ch.gif') repeat-x scroll 0% 0% transparent;
}
a {
    color: #06C;
    text-decoration: none;
}
</style>

<script type="text/javascript">

	// 附件上传
	function uploadPic(){
		// 通过jquery.form.js实现form表单提交
		var options = {
				url:"/sjgjapp/uploadpush.action",
				type:"post",
				dataType:"json",
				success:function(data){
					// 1、图片回显；2、将图片的路径保存到隐藏域中
					$("#allImgUrl").attr("src",data.allImgUrl); // 图片回显
					$("#imgUrl").val(data.imgUrl); // 设置隐藏域的值
				}
		}
		
		$("#jvForm").ajaxSubmit(options);
	}
	
</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 推送消息管理 - 添加</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='/sjgjapp/pushmessage/list.action';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<h2 class="h2_ch"><span id="tabs">
<a href="javascript:void(0);" ref="#tab_1" title="基本信息" class="here">基本信息</a>

</span></h2>
<div class="body-box" style="float:right">
	<form id="jvForm" action="/sjgjapp/pushmessage/save.action" method="post" enctype="multipart/form-data">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody id="tab_1">
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						推送内容:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="content" value="" maxlength="1000" size="100"/>
					</td>
				</tr>
					<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						推送链接:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="contentUrl" value="" maxlength="1000" size="100"/>
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						上传标题图片(90x150尺寸):</td>
						<td width="80%" class="pn-fcontent">
						注:该尺寸图片必须为90x150。
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent">
						<img width="100" height="100" id="allImgUrl"/>
						<input type="hidden" name="titlepicture" id="imgUrl"/>
						<input type="file" name="pic" onchange="uploadPic();"/>
					</td>
				</tr>
			</tbody>
	
			<tbody>
				<tr>
					<td class="pn-fbutton" colspan="2">
						<input type="submit" class="submit" value="提交"/> &nbsp; <input type="reset" class="reset" value="重置"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>