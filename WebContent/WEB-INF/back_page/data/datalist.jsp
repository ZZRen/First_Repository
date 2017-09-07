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
	<div class="rpos">当前位置: 考生买家 - 列表</div>
		
	<div class="clear"></div>
</div>
<div class="body-box">
<form action="/sjgjsend/data/list.action" method="post" style="padding-top:5px;">
	<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
					<span class="pn-frequired">*</span>
						上次导入时间:</td>
						<td width="80%" class="pn-fcontent">
						${fdatatime}
					</td>
				</tr>
				&nbsp&nbsp&nbsp&nbsp
	考生类型：<select id="englishtype" name="englishtype" >
		<option value="">请选择考生类型</option>
		<option value="英语一" <c:if test="${englishtype =='英语一'}">selected="selected"</c:if>>英语一</option>
		<option value="英语二" <c:if test="${englishtype =='英语二'}">selected="selected"</c:if>>英语二</option>
		<option value="未确定" <c:if test="${englishtype =='未确定'}">selected="selected"</c:if>>未确定</option>
	</select>
	考研年份: <input type="text" name="examyear" value="${examyear}" size="8"/>
	
	地区：<select id="area" name="area" >
		<option value="">请选择地区</option>
		<option value="" <c:if test="${area ==''}">selected="selected"</c:if>>全国</option>
		<option value="北京" <c:if test="${area =='北京'}">selected="selected"</c:if>>北京</option>
		<option value="上海" <c:if test="${area =='上海'}">selected="selected"</c:if>>上海</option>
		<option value="广东" <c:if test="${area =='广东'}">selected="selected"</c:if>>广东</option>
		<option value="天津" <c:if test="${area =='天津'}">selected="selected"</c:if>>天津</option>
		<option value="重庆" <c:if test="${area =='重庆'}">selected="selected"</c:if>>重庆</option>
		<option value="辽宁" <c:if test="${area =='辽宁'}">selected="selected"</c:if>>辽宁</option>
		<option value="江苏" <c:if test="${area =='江苏'}">selected="selected"</c:if>>江苏</option>
		<option value="湖北" <c:if test="${area =='湖北'}">selected="selected"</c:if>>湖北</option>
		<option value="四川" <c:if test="${area =='四川'}">selected="selected"</c:if>>四川</option>
		<option value="陕西" <c:if test="${area =='陕西'}">selected="selected"</c:if>>陕西</option>
		<option value="河北" <c:if test="${area =='河北'}">selected="selected"</c:if>>河北</option>
		<option value="山西" <c:if test="${area =='山西'}">selected="selected"</c:if>>山西</option>
		<option value="河南" <c:if test="${area =='河南'}">selected="selected"</c:if>>河南</option>
		<option value="吉林" <c:if test="${area =='吉林'}">selected="selected"</c:if>>吉林</option>
		<option value="黑龙江" <c:if test="${area =='黑龙江'}">selected="selected"</c:if>>黑龙江</option>
		<option value="内蒙古" <c:if test="${area =='内蒙古'}">selected="selected"</c:if>>内蒙古</option>
		<option value="山东" <c:if test="${area =='山东'}">selected="selected"</c:if>>山东</option>
		<option value="安徽" <c:if test="${area =='安徽'}">selected="selected"</c:if>>安徽</option>
		<option value="浙江" <c:if test="${area =='浙江'}">selected="selected"</c:if>>浙江</option>
		<option value="福建" <c:if test="${area =='福建'}">selected="selected"</c:if>>福建</option>
		<option value="湖南" <c:if test="${area =='湖南'}">selected="selected"</c:if>>湖南</option>
		<option value="广西" <c:if test="${area =='广西'}">selected="selected"</c:if>>广西</option>
		<option value="江西" <c:if test="${area =='江西'}">selected="selected"</c:if>>江西</option>
		<option value="贵州" <c:if test="${area =='贵州'}">selected="selected"</c:if>>贵州</option>
		<option value="云南" <c:if test="${area =='云南'}">selected="selected"</c:if>>云南</option>
		<option value="西藏" <c:if test="${area =='西藏'}">selected="selected"</c:if>>西藏</option>
		<option value="海南" <c:if test="${area =='海南'}">selected="selected"</c:if>>海南</option>
		<option value="甘肃" <c:if test="${area =='甘肃'}">selected="selected"</c:if>>甘肃</option>
		<option value="宁夏" <c:if test="${area =='宁夏'}">selected="selected"</c:if>>宁夏</option>
		<option value="青海" <c:if test="${area =='青海'}">selected="selected"</c:if>>青海</option>
		<option value="新疆" <c:if test="${area =='新疆'}">selected="selected"</c:if>>新疆</option>
		<option value="香港" <c:if test="${area =='香港'}">selected="selected"</c:if>>香港</option>
		<option value="澳门" <c:if test="${area =='澳门'}">selected="selected"</c:if>>澳门</option>
		<option value="台湾" <c:if test="${area =='台湾'}">selected="selected"</c:if>>台湾</option>
	</select>
	
	手机号: <input type="text" name="phone" value="${phone}" size="15"/>
	
	已购图书:<select  id="bookid" name="bookid" >
		<option value="">请选择图书</option>
		<c:forEach items="${books}" var="book">
	<%-- 	<option value="${book.bookid}">${book.bookname}</option> --%>
		<option value="${book.bookid}" <c:if test="${bookid ==book.bookid}">selected="selected"</c:if>>${book.bookname}</option>
		</c:forEach>
	</select>
	购书商店:<select  id="buystore" name="buystore" >
		<option value="">请选择商店</option>
		<c:forEach items="${buystores}" var="duystore">
		<%-- <option value="${duystore.storename}">${duystore.storename}</option> --%>
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
			<th width="15%">昵称</th>
			<th width="15%">手机号</th>
			<th width="20%">邮箱</th>
			<th width="10%">地区</th>
			<th width="5%">考研年份</th>
			<th width="10%">考研类型</th>
			<th width="15%">操作</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
		<c:forEach items="${pagination.list }" var="fuser">
			<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
				<%-- <td><input type="checkbox" name="ids" value="${xlcode.xid }"/></td> --%>
				<td align="center">${fuser.tname }</td>
				<td align="center">${fuser.phone}</td>
				<td align="center">${fuser.email}</td>
				<td align="center">${fuser.area}</td>
				<td align="center">${fuser.examyear}</td>
				<td align="center">${fuser.englishtype}</td>
				<td align="center">
				<a href="/sjgjsend/data/edit.action?fuid=${fuser.fuid }" class="pn-opt">查询购买记录</a> | <a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}" href="/sjgjsend/data/deteleOne.action?fuid=${fuser.fuid }">删除</a>
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
<%-- <div>
  <form action="/sjgjsend/data/tosendlist.action" method="post">
	<input type="hidden" name="englishtype" value="${englishtype}"/>
	<input type="hidden" name="examyear" value="${examyear}"/>
	<input type="hidden" name="area" value="${area}"/>
	<input type="hidden" name="phone" value="${phone}"/>
	<input type="hidden" name="bookid" value="${bookid}"/>
	<input type="hidden" name="buystore" value="${buystore}"/>
	<input type="submit" class="query" value="发短信"/>
</form>
 </div> --%>
  &nbsp&nbsp&nbsp&nbsp
 <div>
  <form action="/sjgjsend/data/tosendeamillist.action" method="post">
	<input type="hidden" name="englishtype" value="${englishtype}"/>
	<input type="hidden" name="examyear" value="${examyear}"/>
	<input type="hidden" name="area" value="${area}"/>
	<input type="hidden" name="phone" value="${phone}"/>
	<input type="hidden" name="bookid" value="${bookid}"/>
	<input type="hidden" name="buystore" value="${buystore}"/>
	<input type="submit" class="query" value="发邮件"/>
</form>
</div> 
</body>
</html>