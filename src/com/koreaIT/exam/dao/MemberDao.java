package com.koreaIT.exam.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.exam.dto.Member;
import com.koreaIT.exam.util.Util;

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

	public Member getMemberByLoginIdAndLoginPw(String loginId, String loginPw) {
		for (Member member : this.members) {
			if (member.getLoginId().equals(loginId) && member.getLoginPw().equals(loginPw)) {
				return member;
			}
		}
		return null;
	}

	public Member getMemberByLoginId(String loginId) {
		for (Member member : this.members) {
			if (member.getLoginId().equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	public String getWriterName(int loginedMemberId) {
		for (Member member : this.members) {
			if (loginedMemberId == member.getId()) {
				return member.getLoginId();
			}
		}
		return null;
	}

	public void makeTestData() {
		for (int i = 1; i <= 3; i++) {
			this.lastMemberId++;
			this.members
					.add(new Member(this.lastMemberId, Util.getDateStr(), Util.getDateStr(), "test" + i, "test" + i, "유저" + i));
		}
	}

}
