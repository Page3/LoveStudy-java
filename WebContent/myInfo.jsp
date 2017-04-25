<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
		<title>个人信息</title>
	</head>
	<body>
		<table border="1">
			<tr>
				<td>头像</td>
				<td><img src=${user.headimgurl } width="50" height="50"/></td>
			</tr>
			<tr>
				<td>openid</td>
				<td>${user.openid }</td>
			</tr>
			<tr>
				<td>昵称</td>
				<td>${user.nickname }</td>
			</tr>
			<tr>
				<td>性别</td>
				<td>${user.sex }</td>
			</tr>
			<tr>
				<td>国家</td>
				<td>${user.country }</td>
			</tr>
			<tr>
				<td>省份</td>
				<td>${user.province }</td>
			</tr>
			<tr>
				<td>城市</td>
				<td>${user.city }</td>
			</tr>
			<tr>
				<td>语言</td>
				<td>${user.language }</td>
			</tr>
		</table>
		
	</body>
</html>