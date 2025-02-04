package com.lec.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.ex.dao.MemberDao;

public class MidConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// midConfirm.jsp?mid=aaa
		String mid = request.getParameter("mid");
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.midConfirm(mid);
		if (result == MemberDao.EXISTENT) {
			request.setAttribute("midConfirmResult", "<b>중복된 ID 입니다</b>");
		} else {
			request.setAttribute("midConfirmResult", "사용 가능한 ID 입니다");
		}

	}

}
