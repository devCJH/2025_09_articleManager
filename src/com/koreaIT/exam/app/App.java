package com.koreaIT.exam.app;

import java.util.Scanner;

import com.koreaIT.exam.controller.ArticleController;
import com.koreaIT.exam.controller.MemberController;

public class App {

	public void run() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		articleController.makeTestData();
		memberController.makeTestData();

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

			if (cmd.equals("member join")) {
				memberController.doJoin();
			} else if (cmd.equals("member login")) {
				memberController.doLogin();
			} else if (cmd.equals("member logout")) {
				memberController.doLogout();
			} else if (cmd.equals("article write")) {
				articleController.doWrite();
			} else if (cmd.startsWith("article list")) {
				articleController.showList(cmd);
			} else if (cmd.startsWith("article detail ")) {
				articleController.showDetail(cmd);
			} else if (cmd.startsWith("article modify ")) {
				articleController.doModify(cmd);
			} else if (cmd.startsWith("article delete ")) {
				articleController.doDelete(cmd);
			} else {
				System.out.println("존재하지 않는 명령어 입니다");
			}
		}
		System.out.println("== 프로그램 종료 ==");
		sc.close();
	}
}