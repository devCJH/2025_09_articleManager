package com.koreaIT.exam.controller;

import java.util.List;
import java.util.Scanner;

import com.koreaIT.exam.dto.Article;
import com.koreaIT.exam.service.ArticleService;
import com.koreaIT.exam.session.Session;
import com.koreaIT.exam.util.Util;

public class ArticleController {
	private Scanner sc;
	private ArticleService articleService;

	public ArticleController(Scanner sc) {
		this.sc = sc;
		this.articleService = new ArticleService();
	}

	public void doWrite() {
		if (Session.isLogined() == false) {
			System.out.println("로그인을 해야만 사용할 수 있는 기능입니다");
			return;
		}

		System.out.printf("제목 : ");
		String title = sc.nextLine().trim();
		System.out.printf("내용 : ");
		String body = sc.nextLine().trim();

		int id = this.articleService.writeArticle(Util.getDateStr(), Util.getDateStr(),
				Session.getLoginedMemberId(), title, body);

		System.out.println(id + "번 게시글이 작성되었습니다");
	}

	public void showList(String cmd) {
		List<Article> articles = this.articleService.getArticles();

		if (articles.size() == 0) {
			System.out.println("게시글이 존재하지 않습니다");
			return;
		}

		String searchKeyword = this.articleService.getSearchKeywordByCmd(cmd);

		List<Article> printArticles = articles;

		if (searchKeyword.length() > 0) {
			System.out.println("입력된 검색어 : " + searchKeyword);

			printArticles = this.articleService.getPrintArticles(searchKeyword);

			if (printArticles.size() == 0) {
				System.out.println("검색결과가 없습니다");
				return;
			}
		}

		System.out.println("번호	|	제목	|	작성자	|		작성일");
		for (int i = printArticles.size() - 1; i >= 0; i--) {
			Article article = printArticles.get(i);
			
			String writerName = this.articleService.getWriterName(article.getMemberId());
			
			System.out.printf("%d	|	%s	|	%s	|	%s\n", article.getId(), article.getTitle(),
					writerName, article.getUpdateDate());
		}
	}

	public void showDetail(String cmd) {
		int id = this.articleService.getCmdNum(cmd);

		if (id == -1) {
			System.out.println("게시글 번호를 정확하게 입력해주세요");
			return;
		}

		Article foundArticle = this.articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시글이 존재하지 않습니다\n", id);
			return;
		}

		String writerName = this.articleService.getWriterName(foundArticle.getMemberId());
		
		System.out.printf("== %d번 게시글 상세보기 ==\n", id);
		System.out.printf("번호 : %d\n", foundArticle.getId());
		System.out.printf("작성일 : %s\n", foundArticle.getRegDate());
		System.out.printf("수정일 : %s\n", foundArticle.getUpdateDate());
		System.out.printf("작성자 : %s\n", writerName);
		System.out.printf("제목 : %s\n", foundArticle.getTitle());
		System.out.printf("내용 : %s\n", foundArticle.getBody());
	}

	public void doModify(String cmd) {
		if (Session.isLogined() == false) {
			System.out.println("로그인을 해야만 사용할 수 있는 기능입니다");
			return;
		}
		
		int id = this.articleService.getCmdNum(cmd);

		if (id == -1) {
			System.out.println("게시글 번호를 정확하게 입력해주세요");
			return;
		}

		Article foundArticle = this.articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시글이 존재하지 않습니다\n", id);
			return;
		}

		if (Session.getLoginedMemberId() != foundArticle.getMemberId()) {
			System.out.println("해당 게시물에 대한 권한이 없습니다");
			return;
		}
		
		System.out.printf("수정할 제목 : ");
		String title = sc.nextLine().trim();
		System.out.printf("수정할 내용 : ");
		String body = sc.nextLine().trim();

		this.articleService.modifyArticle(foundArticle, title, body, Util.getDateStr());

		System.out.printf("%d번 게시글의 수정이 완료되었습니다\n", id);
	}

	public void makeTestData() {
		this.articleService.makeTestData();
		System.out.println("테스트용 게시글 데이터 5개를 생성했습니다");
	}

	public void doDelete(String cmd) {
		if (Session.isLogined() == false) {
			System.out.println("로그인을 해야만 사용할 수 있는 기능입니다");
			return;
		}
		
		int id = this.articleService.getCmdNum(cmd);

		if (id == -1) {
			System.out.println("게시글 번호를 정확하게 입력해주세요");
			return;
		}

		Article foundArticle = this.articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시글이 존재하지 않습니다\n", id);
			return;
		}
		
		if (Session.getLoginedMemberId() != foundArticle.getMemberId()) {
			System.out.println("해당 게시물에 대한 권한이 없습니다");
			return;
		}

		this.articleService.deleteArticle(foundArticle);

		System.out.printf("%d번 게시글을 삭제했습니다\n", id);
	}
}
