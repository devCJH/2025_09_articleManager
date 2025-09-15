package com.koreaIT.exam.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.exam.dto.Member;

public class MemberDao {

	private int lastMemberId;
	private List<Member> members;
	
	public MemberDao() {
		this.lastMemberId = 0;
		this.members = new ArrayList<>();
	}
	
	public boolean isLoginIdDup(String loginId) {
		for (Member member : this.members) {
			if (member.getLoginId().equals(loginId)) {
				return true;
			}
		}
		return false;
	}

	public void joinMember(String regDate, String updateDate, String loginId, String loginPw, String name) {
		this.lastMemberId++;
		
		Member member = new Member(this.lastMemberId, regDate, updateDate, loginId, loginPw, name);
		this.members.add(member);
	}

}
