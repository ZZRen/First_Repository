<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="shortcut icon" href="./res/favicon.ico"/>
<title>登录_世纪高教推送系统</title>
<link rel="stylesheet" href="./res/css/style.css" />
<script src="./res/js/jquery.js"></script>
<script src="./res/js/com.js"></script>
<script src="./res/js/md5.js"></script>
<script type="text/javascript">  
function sumbitmd5(){
	// 通过jquery.form.js实现form表单提交
	var password= $("#password").val();
	  var md5password = hex_md5(password);
	    /* alert(md5password); */
	    $("#password").val(md5password);
}


</script>  
</head>
<!-- <body> -->
 <body background="./res/img/世纪高教推送系统.jpg"; width="100%"; height="100%"; >
 <div class="bar"><div class="bar_w">
	<p class="l">
		<span class="l">
			收藏本网站！北京<a href="#" title="更换">[更换]</a>
		</span>
	</p>
	<ul class="r uls">
	<li class="dev">您好,欢迎来到世纪高教推送系统！</li>
	<li class="dev"><a href="#" title="在线客服">在线客服</a></li>
	<li class="dev after"><a href="#" title="English">English</a></li>
	</ul>
</div></div> 
<!--  <div id="Layer1" style="position:absolute; width:100%; height:100%; z-index:-1">    
<img src="/res/img/世纪高教二维码防护系统bg.jpg" height="100%" width="100%"/>    
</div>  -->
<!-- <div class="w loc">
	<div class="h-title" id="logo">
		<div class="h-logo l"><img src=""/></div>
		<div class="l" style="margin: 13px 10px;font-size: 20px;font-family: '微软雅黑';letter-spacing: 2px"></div>
	</div>
</div> --> 
 <div class="sign" style="position:absolute;left:20px;top:140px">
	<!-- <div class="l ad420x205"><a href="#" title="title"><img src="/res/img/世纪高教二维码防护系统bg.jpg" width="400" height="400"/></a></div> --> 
	<div class="r" align="left">
		<h2 title="登录世纪高教推送系统">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;世纪高教推送系统</h2>
		<form id="jvForm" action="./userlogin.action" method="post">
			<%-- <input type="hidden" name="returnUrl" value="${param.directUrl}"/> --%>
			<ul class="uls form">
			<li style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${error}</li>
			<li><label for="username">用户名：</label>
				<span class="bg_text">
					<input type="text" id="username" name="backusername" maxLength="100" value="${user}"/>
				</span>
			</li>
			<li><label for="password">密　码：</label>
				<span class="bg_text">
					<input type="password" id="password" name="password" maxLength="32" />
				</span>
			</li>
			<!-- <li><label for="captcha">验证码：</label> -->
			<!-- 	<span class="bg_text small">
					<input type="text" id="captcha" name="captcha" maxLength="7"/>
				</span> -->
				<%-- <img src="${base}/captcha.svl" onclick="this.src='${base}/captcha.svl?d='+new Date()" class="code" alt="换一张" /><a href="javascript:void(0);" onclick="$('.code').attr('src','${base}/captcha.svl?d='+new Date())" title="换一张">换一张</a></li> --%>
			<li><label for="">&nbsp;</label><input type="submit" value="登 录" onclick="sumbitmd5();" class="hand btn66x23"/><!-- <a href="#" title="忘记密码？">忘记密码？</a> --></li>
			</ul>
		</form>
	</div>
 </div>
</body>
</html>