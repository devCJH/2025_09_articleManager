package com.koreaIT.exam.controller;

import java.util.Scanner;

import com.koreaIT.exam.service.ArticleService;
import com.koreaIT.exam.util.Util;

public class ArticleController {
	private Scanner sc;
	private ArticleService articleService;
	
	public ArticleController(Scanner sc) {
		this.sc = sc;
		this.articleService = new ArticleService();
	}

	public void doWrite() {
		System.out.println("제목을 입력하세요");
		String title = sc.nextLine().trim();
		System.out.printf("내용 : ");
		String body = sc.nextLine().trim();

		int id = this.articleService.writeArticle(Util.getDateStr(), Util.getDateStr(), title, body);

		System.out.println(id + "번 게시글이 작성되었습니다");
	}

	public void makeTestData() {
		this.articleService.makeTestData();
		System.out.println("테스트용 게시글 데이터 5개를 생성했습니다");
	}

}
