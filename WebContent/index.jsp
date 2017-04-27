<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>自在畅学首页</title>
</head>
<body>
	<a href="<%=request.getContextPath() %>/common?oprateType=getList">getList</a><br/>
	
	<a href="<%=request.getContextPath() %>/file?oprateType=getArticle&fid=fid">getArticle</a><br/>
	<a href="<%=request.getContextPath() %>/file?oprateType=getArticles&school=school&college=college&course=course&time=time&signature=signature">getArticles</a><br/>
	
	<a href="<%=request.getContextPath() %>/user?oprateType=getFavourite&uid=uid&time=time&signature=signature">getFavourite</a><br/>
	<a href="<%=request.getContextPath() %>/user?oprateType=getUploaded&uid=uid&time=time&signature=signature">getUploaded</a><br/>
	<a href="<%=request.getContextPath() %>/user?oprateType=toggleFavourite&uid=uid&fid=fid&time=time&signature=signature">toggleFavourite</a><br/>
	<a href="<%=request.getContextPath() %>/user?oprateType=getUser&uid=uid&time=time&signature=signature">getUser</a><br/>
	
	<h1><a href="<%=request.getContextPath() %>/login">微信授权登录</a></h1>
</body>
</html>