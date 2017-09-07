<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>sjgj-list</title>
<script type="text/javascript">	
function changePageNo(){
	$("input[name='pageNo']").val(1);
}
</script>
<script type="text/javascript">
	
	
</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 外刊种类管理- 列表</div>
	 <form class="ropt">
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='/sjgjapp/pushmessage/add.action'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
 <form action="/sjgjsend/composition/list.action" method="post" style="padding-top:5px;">
	
	推送内容: <input type="text" name="content" value="${content }" maxlength="1000" size="50"/>
	<input type="submit" class="query" value="查询"/>
</form> 
<form id="jvForm">
<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="30%">推送内容</th>
			<th width="10%">标题图片</th>
			<th width="10%">更新时间</th>
			<th width="5%">推送状态</th>
			<th width="10%">操作</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
		<c:forEach items="${pagination.list }" var="pushmessage">
			<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
				<td align="center">${pushmessage.content}</td>
				<td align="center"><img width="50" height="50" src="${pushmessage.titlepicture }"/></td>
				<td align="center">${pushmessage.pushtime}</td>
				<td align="center">${pushmessage.pushstatus}</td>
				<td align="center">
				<a href="/sjgjapp/pushmessage/edit.action?id=${pushmessage.id }" class="pn-opt">编辑</a> | <a class="pn-opt" onclick="if(!confirm('您确定推送吗？')) {return false;}" href="/sjgjapp/pushmessage/informpush.action?id=${pushmessage.id }">推送</a> | <a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}" href="/sjgjapp/pushmessage/deteleOne.action?id=${pushmessage.id }">删除</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</form>
<div class="page pb15">
	<span class="r inb_a page_b">
		<c:forEach items="${pagination.pageView }" var="page">
			${page }
		</c:forEach>
	</span>
</div>
</div>
</body>
</html>