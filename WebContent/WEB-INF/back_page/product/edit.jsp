<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>sjgj-edit</title>
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
$(function(){
	var tObj;
	$("#tabs a").each(function(){
		if($(this).attr("class").indexOf("here") == 0){tObj = $(this)}
		$(this).click(function(){
			var c = $(this).attr("class");
			if(c.indexOf("here") == 0){return;}
			var ref = $(this).attr("ref");
			var ref_t = tObj.attr("ref");
			tObj.attr("class","nor");
			$(this).attr("class","here");
			$(ref_t).hide();
			$(ref).show();
			tObj = $(this);
			if(ref == '#tab_2'){
				var fck = new FCKeditor("productdesc");
				fck.BasePath = "../res/fckeditor/";
				fck.Config["ImageUploadURL"] = "/sjgjapp/uploadFck.action"; // 指定fck附件上传的请求路径
				fck.Height = 400 ;
				fck.ReplaceTextarea();
			}
		});
	});
});
</script> -->
<!-- <script type="text/javascript">

	// 附件上传
	function uploadPic(){
		// 通过jquery.form.js实现form表单提交
		var options = {
				url:"/sjgjapp/uploadPic.action",
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
	<div class="rpos">当前位置: 图书库管理 - 编辑</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='/sjgjsend/book/list.action';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<h2 class="h2_ch"><span id="tabs">
<a href="javascript:void(0);" ref="#tab_1" title="基本信息" class="here">基本信息</a>
<!--  <a href="javascript:void(0);" ref="#tab_2" title="详情描述" class="nor">详情描述</a> -->
</span></h2>
<div class="body-box" style="float:right">
	<form id="jvForm" action="/sjgjsend/book/update.action" method="post" enctype="multipart/form-data">
		<input type="hidden" name="bookid" value="${fbook.bookid}"/>
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody id="tab_1">
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						图书类型:</td><td width="80%" class="pn-fcontent">
						<select name="booktype">
							<option value="">请选择图书类型</option>
							<option value="英语一" <c:if test="${fbook.booktype =='英语一'}">selected="selected"</c:if>>英语一</option>
							<option value="英语二" <c:if test="${fbook.booktype =='英语二'}">selected="selected"</c:if>>英语二</option>
							<option value="数学一" <c:if test="${fbook.booktype =='数学一'}">selected="selected"</c:if>>数学一</option>
							<option value="数学二" <c:if test="${fbook.booktype =='数学二'}">selected="selected"</c:if>>数学二</option>
							<option value="数学三" <c:if test="${fbook.booktype =='数学三'}">selected="selected"</c:if>>数学三</option>
							
						</select>
					</td>
				</tr>
					<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						图书年份:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="bookyear" value="${fbook.bookyear }" maxlength="300" size="10"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						图书名称:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="bookname" value="${fbook.bookname }" maxlength="300" size="60"/>
					</td>
				</tr>
				
			</tbody>
			<%-- <tbody id="tab_2" style="display: none">
				<tr>
					<td >
						<textarea rows="5" cols="5" id="productdesc" name="decribeUrl">${book.decribeUrl }</textarea>
					</td>
				</tr>
			</tbody> --%>
			<tbody>
				<tr>
					<td class="pn-fbutton" colspan="2">
						<input type="submit" class="submit" value="提交"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>