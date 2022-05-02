<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 글쓰기</title>
</head>
<body>
	<h2>자유게시판 글쓰기</h2>
	<hr>
	<table width="600" cellpadding="0" cellspacing="0" border="1">
		<form action="write.do" method="post">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="bid" size="80"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="bname" size="80"></td>
			</tr>
			<tr>
				<td>글제목</td>
				<td><input type="text" name="btitle" size="80"></td>
			</tr>
			<tr>
				<td>글내용</td>
				<td><textarea name="bcontent" rows="10" cols="60"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="글작성">
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="list.do">글목록보기</a></td>
			</tr>
		</form>
	</table>
</body>
</html>