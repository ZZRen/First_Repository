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

<!-- <script type="text/javascript">

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
	
</script> -->
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 群发邮件 </div>
	<!-- <form class="ropt">
		<input type="submit" onclick="this.form.action='/sjgjapp/pushmessage/list.action';" value="返回列表" class="return-button"/>
	</form> -->
	<div class="clear"></div>
</div>
<h2 class="h2_ch"><span id="tabs">
<a href="javascript:void(0);" ref="#tab_1" title="基本信息" class="here">发送邮件</a>

</span></h2>
<div class="body-box" style="float:right">
	<form id="jvForm" action="/sjgjapp/pushmessage/sendemail.action" method="post" enctype="multipart/form-data">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody id="tab_1">
			<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						书名:</td><td width="80%" class="pn-fcontent">
								<select name="bookname">
								<option value="">请选择书名</option>
								<c:forEach items="${books}" var="book">
										<option value="${book}">${book}</option>			
								</c:forEach>	
								</select>
					</td>
				</tr>
			<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						地区:</td><td width="80%" class="pn-fcontent">
						
								<select name="province">
										<option value="">请选择地区</option>
										<option value="">全国</option>
										<option value="北京">北京</option>
										<option value="上海 ">上海</option>
										<option value="广东">广东</option>
										<option value="天津">天津</option>
										<option value="重庆">重庆</option>
										<option value="辽宁">辽宁</option>
										<option value="江苏">江苏</option>
										<option value="湖北">湖北</option>
										<option value="四川">四川</option>
										<option value="陕西">陕西</option>
										<option value="河北">河北</option>
										<option value="山西">山西</option>
										<option value="河南">河南</option>
										<option value="吉林">吉林</option>
										<option value="黑龙江">黑龙江</option>
										<option value="内蒙古">内蒙古</option>
										<option value="山东">山东</option>
										<option value="安徽">安徽</option>
										<option value="浙江">浙江</option>
										<option value="福建">福建</option>
										<option value="湖南">湖南</option>
										<option value="广西">广西</option>
										<option value="江西">江西</option>
										<option value="贵州">贵州</option>
										<option value="云南">云南</option>
										<option value="西藏">西藏</option>
										<option value="海南">海南</option>
										<option value="甘肃">甘肃</option>
										<option value="宁夏">宁夏</option>
										<option value="青海">青海</option>
										<option value="新疆">新疆</option>
										<option value="香港">香港</option>
										<option value="澳门">澳门</option>
										<option value="台湾">台湾</option>		
								</select>
					</td>
				</tr>		
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						邮件标题:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="title" value="" maxlength="1000" size="100"/>
					</td>
				</tr>
				<c:forEach items="${books}" var="book">
			
				</c:forEach>
				
					<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						发送内容:</td><td width="80%" class="pn-fcontent">
					<textarea class="required" name="content" rows="15" cols="94" ></textarea>
					</td>
				</tr>
			</tbody>
	
			<tbody>
				<tr>
					<td class="pn-fbutton" colspan="2">
						<input type="submit" class="submit" value="发送"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>