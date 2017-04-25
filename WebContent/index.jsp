<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>自在畅学首页</title>
</head>
<body>
	<a href="<%=request.getContextPath() %>/test">test</a><br/>
	<a href="<%=request.getContextPath() %>/common">comnon</a><br/>
	<a href="<%=request.getContextPath() %>/file?oprateType=getArticle&fid=qwerty">getArticle</a><br/>
	<a href="<%=request.getContextPath() %>/file?oprateType=getArticles&school=苏州大学&college=计科院&course=离散数学">getArticles</a><br/>
	
	<a href="<%=request.getContextPath() %>/user?oprateType=getFavourite&uid=wspage3">getFavourite</a><br/>
	<a href="<%=request.getContextPath() %>/user?oprateType=getUploaded&uid=wspage3">getUploaded</a><br/>
	<a href="<%=request.getContextPath() %>/user?oprateType=toggleFavourite&uid=wspage3&fid=qwerty">toggleFavourite</a><br/>
	<a href="<%=request.getContextPath() %>/user?oprateType=getUser&uid=wspage3">getUser</a><br/>
	
	<h1><a href="<%=request.getContextPath() %>/login">微信授权登录</a></h1>
</body>
</html>