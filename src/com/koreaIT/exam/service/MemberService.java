package com.koreaIT.exam.service;

import com.koreaIT.exam.dao.MemberDao;

public class MemberService {

	private MemberDao memberDao;
	
	public MemberService() {
		this.memberDao = new MemberDao();
	}
	
	public boolean isLoginIdDup(String loginId) {
		return this.memberDao.isLoginIdDup(loginId);
	}

	public void joinMember(String regDate, String updateDate, String loginId, String loginPw, String name) {
		this.memberDao.joinMember(regDate, updateDate, loginId, loginPw, name);
	}

}
