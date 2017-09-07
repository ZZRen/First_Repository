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

</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 图书编号 - 添加</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='/sjgjsend/pstorebook/list.action';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<h2 class="h2_ch"><span id="tabs">
<a href="javascript:void(0);" ref="#tab_1" title="基本信息" class="here">基本信息</a>
 <!-- <a href="javascript:void(0);" ref="#tab_2" title="详情描述" class="nor">详情描述</a> -->
</span></h2>
<div class="body-box" style="float:right">
	<form id="jvForm" action="/sjgjsend/pstorebook/update.action" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${pstorebook.id}"/>
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody id="tab_1">
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						商品编号:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="pid" value="${pstorebook.pid}" maxlength="300" size="20"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						商店名:</td><td width="80%" class="pn-fcontent">
								<select name="buystore">
									<option value="">请选择</option>
									<c:forEach items="${buystores}" var="duystore">
								<option value="${duystore.storename}" <c:if test="${pstorebook.buystore ==duystore.storename}">selected="selected"</c:if>>${duystore.storename}</option>
		
								</c:forEach>
								</select>
					</td>
				</tr>
					
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						图书名:</td><td width="80%" class="pn-fcontent">
								<select name="bookid">
									<option value="">请选择</option>
									<c:forEach items="${books}" var="book">
				<option value="${book.bookid}" <c:if test="${pstorebook.bookid ==book.bookid}">selected="selected"</c:if>>${book.bookname}</option>
		
		
								</c:forEach>
								</select>
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