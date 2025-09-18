package com.koreaIT.exam.session;

public class Session {
	private static int loginedMemberId;
	
	static {
		loginedMemberId = -1;
	}
	
	public static int getLoginedMemberId() {
		return loginedMemberId;
	}
	
	public static boolean isLogined() {
		return loginedMemberId != -1;
	}
	
	public static void login(int memberId) {
		loginedMemberId = memberId;
	}
	
	public static void logout() {
		loginedMemberId = -1;
	}
}
