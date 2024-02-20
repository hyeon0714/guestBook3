<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<%@ page import = "com.javaex.gbVo.GbVo" %>
	<%@ page import = "java.util.List" %>
	
	<%
		List<GbVo> gList = (List<GbVo>)request.getAttribute("gList");
	%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="http://localhost:8080/guestBook3/gbc" method="get">
		<table border="1" width="540px">
			<tr>
				<td>이름</td><td><input type="text" name="name"></td>
				<td>비밀번호</td><td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="4"><textarea cols="72" rows="5" name="content"></textarea></td>
			</tr>
			
			<tr>
				<td colspan="4"><button type="submit">등록</button></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="insert">
	</form>
	<br>

	<% for(int i=0; i<gList.size(); i++) {%>
	
	<table border="1" width="540px">
		<tr>
			<td>[<%=i+1 %>]</td>
			<td><%=gList.get(i).getName() %></td>
			<td><%=gList.get(i).getDate() %></td>
			<td>
				<a href="http://localhost:8080/guestBook3/gbc?action=delete&no=<%=gList.get(i).getNo() %>">삭제</a>
			</td>
		</tr>
		<tr>
			<td colspan="4"><%=gList.get(i).getContent() %></td>
		</tr>
	</table>
	<br>
	<%} %>

</body>
</html>