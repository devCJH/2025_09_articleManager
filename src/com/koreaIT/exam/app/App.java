package com.koreaIT.exam.app;

import java.util.Scanner;

import com.koreaIT.exam.controller.ArticleController;
import com.koreaIT.exam.controller.Controller;
import com.koreaIT.exam.controller.MemberController;
import com.koreaIT.exam.session.Session;

public class App {

	public void run() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				break;
			}
			
			if (cmd.trim().length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			String[] cmdBits = cmd.split(" ");
			
			if (cmdBits.length < 2) {
				System.out.println("존재하지 않는 명령어 입니다");
				continue;
			}
			
			String controllerName = cmdBits[0];
			String methodName = cmdBits[1];
			
			switch (controllerName + "/" + methodName) {
			case "member/logout":
			case "article/write":
			case "article/modify":
			case "article/delete":
				if (Session.isLogined() == false) {
					System.out.println("로그인부터 하고 와");
					continue;
				}
				break;
			case "member/join":
			case "member/login":
				if (Session.isLogined()) {
					System.out.println("로그아웃부터 하고 와");
					continue;
				}
				break;
			}
			
			Controller controller = null;
			
			if (controllerName.equals("member")) {
				controller = memberController;
			} else if (controllerName.equals("article")) {
				controller = articleController;
			} else {
				System.out.println("존재하지 않는 명령어 입니다");
				continue;
			}
			
			controller.doAction(methodName, cmd);
		}
		System.out.println("== 프로그램 종료 ==");
		sc.close();
	}
}