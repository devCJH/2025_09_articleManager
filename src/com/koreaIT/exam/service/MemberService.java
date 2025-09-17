package com.koreaIT.exam.service;

import com.koreaIT.exam.container.Container;
import com.koreaIT.exam.dao.MemberDao;
import com.koreaIT.exam.dto.Member;

public class MemberService {

	private MemberDao memberDao;
	
	public MemberService() {
		this.memberDao = Container.memberDao;
	}
	
	public boolean isLoginIdDup(String loginId) {
		return this.memberDao.isLoginIdDup(loginId);
	}

	public void joinMember(String regDate, String updateDate, String loginId, String loginPw, String name) {
		this.memberDao.joinMember(regDate, updateDate, loginId, loginPw, name);
	}

	public Member getMemberByLoginIdAndLoginPw(String loginId, String loginPw) {
		return this.memberDao.getMemberByLoginIdAndLoginPw(loginId, loginPw);
	}

	public Member getMemberByLoginId(String loginId) {
		return this.memberDao.getMemberByLoginId(loginId);
	}

	public String getWriterName(int loginedMemberId) {
		return this.memberDao.getWriterName(loginedMemberId);
	}

	public void makeTestData() {
		this.memberDao.makeTestData();
	}

}
