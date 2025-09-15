package com.koreaIT.exam.controller;

import java.util.Scanner;

import com.koreaIT.exam.service.MemberService;
import com.koreaIT.exam.util.Util;

public class MemberController {
	private Scanner sc;
	private MemberService memberService;
	
	public MemberController(Scanner sc) {
		this.sc = sc;
		this.memberService = new MemberService();
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
}
