<%@page import="com.lec.dao.BoardDao2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String conPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="<%=conPath %>/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%request.setCharacterEncoding("utf-8"); %>
	<jsp:useBean id="dto" class="com.lec.dto.BoardDto2"/>
	<jsp:setProperty property="*" name="dto"/>
		<!-- 
		들어온 파라미터 : num, writer(필요 X), subject, content, email, pw
		dao의 updateBoard() 호출을 위해서 ip가 추가적으로 필요 
	 -->
	<%
		dto.setIp(request.getRemoteAddr());
		// out.println(dto);
		BoardDao2 bDao = BoardDao2.getInstance();
		int result = bDao.updateBoard(dto);
		/* pageNum 추가 */
		String pageNum = request.getParameter("pageNum");
		if(result == BoardDao2.SUCCESS) {
	%>
		<script>
			alert('글 수정 성공');
			location.href = '<%=conPath%>/board2/content.jsp?num=<%=dto.getNum()%>&pageNum=<%=pageNum%>';
		</script>
	<%}else{ %>
		<script>
			alert('글 수정 실패(길다)');
			history.go(-1);
		</script>
	<%} %>
</body>
</html>