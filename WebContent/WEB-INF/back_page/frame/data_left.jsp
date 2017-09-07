<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>product-left</title>
<link href="../res/itcast/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<!-- <script src="/thirdparty/ckeditor/ckeditor.js" type="text/javascript"></script> -->
<!-- <script src="/thirdparty/My97DatePicker/WdatePicker.js" type="text/javascript"></script> -->
<script type="text/javascript" src="../res/fckeditor/fckeditor.js"></script>
<script src="../res/common/js/jquery.js" type="text/javascript"></script>
<script src="../res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="../res/common/js/jquery.form.js" type="text/javascript"></script>
<script src="../res/common/js/itcast.js" type="text/javascript"></script>
<script src="../res/itcast/js/admin.js" type="text/javascript"></script>
</head>
<body class="lbody">
<div class="left">
<%@ include file="/WEB-INF/back_page/date.jsp" %>
     <ul class="w-lful">
		<li><a href="/sjgjsend/data/scdata_add.action" target="rightFrame">商城数据导入</a></li>
		<!-- <li><a href="/sjgjsend/data/appdata_add.action" target="rightFrame">App数据导入</a></li> -->
		<li><a href="/sjgjsend/data/list.action?buystore=世纪高教图书专营店" target="rightFrame">数据管理</a></li>
		<!-- <li><a href="/sjgjsend/data/tosendlist.action?phone=137169276410" target="rightFrame">发送短信</a></li>
		 --><li><a href="/sjgjsend/data/tosendeamillist.action?phone=137169276410" target="rightFrame">发送邮件</a></li>
		<!-- <li><a href="/brand/list.do" target="rightFrame">其它</a></li>
		<li><a href="../type/list.jsp" target="rightFrame">类型管理</a></li> -->
     </ul>
</div>
</body>
</html>