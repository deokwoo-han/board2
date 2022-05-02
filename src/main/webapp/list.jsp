<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 글목록</title>
</head>
<body>
	<h2>자유게시판 글목록</h2>
	<hr>
	<table width="600" cellpadding="0" cellspacing="0" border="1">
		<tr align="center" bgcolor="pink">
			<td><b>제 목</b></td>
			<td><b>아이디</b></td>
			<td><b>글쓴이</b></td>
			<td><b>조회수</b></td>
		</tr>
		<!-- begin="1" end="10"> 리스트를 만드는 법-->
		<c:forEach items="${list}" var="dto"> 
		<tr>
			<td>
			<a href="content_view.do?bid=${dto.bid }">${dto.btitle}</a>
			</td>
			<td align="center">${dto.bid }</td>
			<td align="center">${dto.bname }</td>
			<td align="center">${dto.bhit }</td>
		</tr>
		</c:forEach>
		<tr>
			<td align="right" colspan="4"><a href="write_view.do">글쓰기</a></td>
		</tr>
	</table>
</body>
</html>