package com.koreaIT.exam.controller;

import java.util.Scanner;

import com.koreaIT.exam.dto.Member;
import com.koreaIT.exam.service.MemberService;
import com.koreaIT.exam.session.Session;
import com.koreaIT.exam.util.Util;

public class MemberController extends Controller {
	private MemberService memberService;
	
	public MemberController(Scanner sc) {
		this.sc = sc;
		this.memberService = new MemberService();
	}

	@Override
	public void doAction(String methodName, String cmd) {
		switch (methodName) {
		case "join":
			this.doJoin();
			break;
		case "login":
			this.doLogin();
			break;
		case "logout":
			this.doLogout();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다");
			break;
		}
	}
	
	public void doJoin() {
		String loginId = null;
		String loginPw = null;
		String name = null;
		
		while (true) {
			System.out.printf("아이디 : ");
			loginId = sc.nextLine().trim();
			
			if (loginId.length() == 0) {						
				System.out.println("아이디는 필수 입력 정보입니다");
				continue;
			}

			boolean isLoginIdDup = this.memberService.isLoginIdDup(loginId);
			
			if (isLoginIdDup) {
				System.out.printf("[ %s ]은(는) 이미 사용중인 아이디입니다\n", loginId);
				continue;
			}
			
			System.out.printf("[ %s ]은(는) 사용 가능한 아이디입니다\n", loginId);
			break;
		}
		
		while(true) {
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine().trim();
			
			if (loginPw.length() == 0) {
				System.out.println("비밀번호는 필수 입력 정보입니다");
				continue;
			}
			
			System.out.printf("비밀번호 확인 : ");
			String loginPwChk = sc.nextLine().trim();
			
			if (loginPw.equals(loginPwChk) == false) {
				System.out.println("비밀번호가 일치하지 않습니다");
				continue;
			}
			
			break;
		}
		
		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();
			
			if (name.length() == 0) {
				System.out.println("이름은 필수 입력 정보입니다");
				continue;
			}
			
			break;
		}

		this.memberService.joinMember(Util.getDateStr(), Util.getDateStr(), loginId, loginPw, name);
		
		System.out.printf("[ %s ]님의 가입이 완료되었습니다\n", loginId);
	}

	public void doLogin() {
		if (Session.isLogined()) {
			System.out.println("이미 로그인 되어 있습니다");
			return;
		}
		
		System.out.printf("아이디 : ");
		String loginId = sc.nextLine().trim();
		System.out.printf("비밀번호 : ");
		String loginPw = sc.nextLine().trim();
		
		if (loginId.length() == 0 || loginPw.length() == 0) {
			System.out.println("아이디 or 비밀번호를 입력하세요");
			return;
		}
		
		Member member = this.memberService.getMemberByLoginIdAndLoginPw(loginId, loginPw);
		
		if (member == null) {
			System.out.println("아이디 or 비밀번호가 일치하지 않습니다");
			return;
		}
		
		Session.login(member.getId());
		
		System.out.printf("[ %s ] 님 환영합니다~\n", member.getLoginId());
	}

	public void doLogout() {
		if (Session.isLogined() == false) {
			System.out.println("이미 로그아웃 상태입니다");
			return;
		}
		
		Session.logout();
		
		System.out.println("정상적으로 로그아웃 되었습니다");
	}
}
