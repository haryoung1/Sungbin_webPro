<%@page import="com.lec.member.MemberDao"%>
<%@page import="com.lec.member.MemberDto"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.Date"%>
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
	<!-- modify에서 전달받은 파라미터 
		id, pw(바꾸고 싶은 새비번), name, phone1, phone2, phone3, gender, email, address(MemberDto)
		tempBirth, oldPw(현비번) (dto에 없는 파라미터 변수)
	-->
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:useBean id="dto" class="com.lec.member.MemberDto" scope="page"/>
	<jsp:setProperty property="*" name="dto"/>
	<%
		String tempBirth = request.getParameter("tempBirth");
		if(!tempBirth.equals("")){
			dto.setBirth(Date.valueOf(tempBirth));
		}
		MemberDto member = (MemberDto)session.getAttribute("member"); // 세션의 pw 필요
		String sessionPw = null;
		if(member != null) {
			sessionPw = member.getPw(); // 세션 meber의 pw
		}
		String oldPw = request.getParameter("oldPw");
		if(sessionPw.equals(oldPw)) {
			// 현 비밀번호를 맞게 입력한 경우 정보 수정 진행
			if(dto.getPw()==null) {
				// 새 비밀번호를 현 비밀번호로 
				dto.setPw(oldPw);
			}
			MemberDao mDao = MemberDao.getInstance();
			int result = mDao.modifyMember(dto);
			if (result == MemberDao.SUCCESS) {
				// 정보 수정성공
				session.setAttribute("member", dto); // 수정 된 정보를 session 속성으로 수정
		%>	
			<script>
				alert('정보수정이 완료 되었습니다.');
				location.href = 'main.jsp';
			</script>
			<% }else { // 수정 실패 %>
			<script>
				alert('회원정보 수정이 실패 되었습니다');
				location.href = 'modify.jsp';
			</script>
		<%} %>
		<% }else {
			// 현 비밀번호를 틀리게 입력한 경우 정보수정 했던 페이지로 이동
		%>
		<script>
			alert('현 비밀번호가 바르지 않습니다. 확인하세요')
			history.back();
		</script>
	<%}%>
</body>
</html>