<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>sjgjknowledge-list</title>
<script type="text/javascript">	
function changePageNo(){
	$("input[name='pageNo']").val(1);
}
</script>
<!-- <script type="text/javascript">
	
	// 全选操作
	function checkBox(ids, checked){
		$("input[name='"+ids+"']").attr("checked",checked);
	}
	
	// 批量删除操作
	function optDelete(bname,typename,pageNo){
		// 获取选中的checkBox的个数
		var size = $("input[name='ids']:checked").size();
		if(size <= 0){
			alert("至少选择一个序列号!");
			return;
		}
		
		// 判断是否删除操作
		if(!confirm('您确定删除吗？')){ // 确定：true  取消：false
			return;
		}
		
		// 删除操作
		$("#jvForm").attr("action","/sjgjapp/xlh/deleteBatch.action?bname="+bname+"&typename="+typename+"&pageNo="+pageNo);
		$("#jvForm").attr("method","post").submit();
		
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
	<div class="rpos">当前位置: 商品编号- 列表</div>
		<form class="ropt">
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='/sjgjsend/pstorebook/add.action'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<form action="/sjgjsend/pstorebook/list.action" method="post" style="padding-top:5px;">
	商品编号: <input type="text" name="pid" value="${pid}" size="20"/>
	商店名:<select  id="buystore" name="buystore" >
		<option value="">请选择商店</option>
		<c:forEach items="${buystores}" var="duystore">
		<option value="${duystore.storename}" <c:if test="${buystore ==duystore.storename}">selected="selected"</c:if>>${duystore.storename}</option>
		
		</c:forEach>
	</select>
	<input type="submit" class="query" value="查询"/>
</form>
<form id="jvForm">
<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<!-- <th width="20"><input type="checkbox" onclick="Pn.checkbox('ids',this.checked)"/></th> -->
			<th width="20%">商品编号</th>
			<th width="20%">商店</th>
			<th >包含图书</th>
			<th width="10%">操作</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
		<c:forEach items="${pagination.list }" var="pstorebook">
			<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
				<%-- <td><input type="checkbox" name="ids" value="${xlcode.xid }"/></td> --%>
				<td align="center">${pstorebook.pid}</td>
				<td align="center">${pstorebook.buystore}</td>
				<td align="center">${pstorebook.bookname}</td>
				<td align="center">
				<a href="/sjgjsend/pstorebook/edit.action?id=${pstorebook.id }" class="pn-opt">修改</a> | <a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}" href="/sjgjsend/pstorebook/deteleOne.action?id=${pstorebook.id }">删除</a>
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