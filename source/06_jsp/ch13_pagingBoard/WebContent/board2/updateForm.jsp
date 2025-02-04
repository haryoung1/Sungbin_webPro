<%@page import="com.lec.dto.BoardDto2"%>
<%@page import="com.lec.dao.BoardDao2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String conPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%=conPath%>/css/style.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<%
		String num = request.getParameter("num");
		BoardDao2 bDao = BoardDao2.getInstance();
		BoardDto2 dto = bDao.getBoardOneLine(num);
		/* pageNum 추가 */
		String pageNum = request.getParameter("pageNum");
	%>
	<form action="<%=conPath%>/board2/updatePro.jsp" method="post">
		<input type="hidden" name="pageNum" value="<%=pageNum %>">
		<input type="hidden" name="num" value="<%=num %>">
		<table>
			<caption><%=num %>번 글 수정</caption>
			<tr>
				<th>작성</th>
				<td>
					<input type="text" name="writer" readonly="readonly" value="<%=dto.getWriter() %>">
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="subject" required="required" value="<%=dto.getSubject() %>">
				</td>
			</tr>
			<tr>
				<th>본문</th>
				<td>
					<textarea rows="5" name="content" 
							required="required"><%=dto.getContent() %>
					</textarea>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<input type="email" name="email" value="<%
						String email = dto.getEmail();
						if(email != null) out.print(email);
					%>">
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="pw" required="required" value="<%=dto.getPw() %>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정" class="btn">
					<input type="reset"  value="취소" class="btn" onclick="history.go(-1)">
					<input type="button" value="목록" class="btn" 
						onclick="location.href='<%=conPath %>/board2/list.jsp?pageNum=<%=pageNum %>'"> 
				</td>
			</tr>
		</table>
	</form>
</body>
</html>