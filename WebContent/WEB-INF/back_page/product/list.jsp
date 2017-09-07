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
	
	// 全选操作
	function checkBox(ids, checked){
		$("input[name='"+ids+"']").attr("checked",checked);
	}
	
	// 批量删除操作
	function optDelete(bname,typename,pageNo){
		// 获取选中的checkBox的个数
		var size = $("input[name='ids']:checked").size();
		if(size <= 0){
			alert("至少选择一本书!");
			return;
		}
		
		// 判断是否删除操作
		if(!confirm('您确定删除吗？')){ // 确定：true  取消：false
			return;
		}
		
		// 删除操作
		$("#jvForm").attr("action","/sjgjapp/book/deleteBatch.action?bname="+bname+"&typename="+typename+"&pageNo="+pageNo);
		$("#jvForm").attr("method","post").submit();
		
	}
	
	
	
</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 图书管理 - 列表</div>
	<form class="ropt">
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='/sjgjsend/book/add.action'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<form action="/sjgjsend/book/list.action" method="post" style="padding-top:5px;">
	<select name="booktype" >
		<option value="">请选择图书类型</option>
		<option value="英语一" <c:if test="${booktype =='英语一'}">selected="selected"</c:if>>英语一</option>
		<option value="英语二" <c:if test="${booktype =='英语二'}">selected="selected"</c:if>>英语二</option>
		<option value="数学一" <c:if test="${booktype =='数学一'}">selected="selected"</c:if>>数学一</option>
		<option value="数学二" <c:if test="${booktype =='数学二'}">selected="selected"</c:if>>数学二</option>
		<option value="数学三" <c:if test="${booktype =='数学三'}">selected="selected"</c:if>>数学三</option>
	</select>
	书名: <input type="text" name="bookname" value="${bookname }" maxlength="300" size="60"/>
	<input type="submit" class="query" value="查询"/>
</form>
<form id="jvForm">
<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" onclick="Pn.checkbox('ids',this.checked)"/></th>
			
			<th width="20%">图书类型</th>
			<th width="10%">图书年份</th>
			<th>图书名称</th>
			
			<th width="10%">操作</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
		<c:forEach items="${pagination.list }" var="book">
			<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
				<td><input type="checkbox" name="ids" value="${book.bookid }"/></td>
				<td align="center">${book.booktype }</td>
				<td align="center">${book.bookyear }</td>
				<td align="center">${book.bookname}</td>
				<td align="center">
				<a href="/sjgjsend/book/edit.action?bookid=${book.bookid }" class="pn-opt">编辑</a> | <a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}" href="/sjgjsend/book/deteleOne.action?bookid=${book.bookid}">删除</a>
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
<div style="margin-top:1px;"><%-- <input class="del-button" type="button" value="删除" onclick="optDelete('${bname}','${typename}','${pageNo}');"/> --%></div>

</div>
</body>
</html>