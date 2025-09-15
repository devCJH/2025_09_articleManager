package com.koreaIT.exam.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.exam.controller.ArticleController;
import com.koreaIT.exam.controller.MemberController;
import com.koreaIT.exam.dto.Article;
import com.koreaIT.exam.util.Util;

public class App {

	public void run() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		articleController.makeTestData();

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
			} else if (cmd.equals("article write")) {
				articleController.doWrite();
			} else if (cmd.startsWith("article list")) {

				if (this.articles.size() == 0) {
					System.out.println("게시글이 존재하지 않습니다");
					continue;
				}

				String searchKeyword = cmd.substring("article list".length()).trim();

				List<Article> printArticles = this.articles;

				if (searchKeyword.length() > 0) {
					System.out.println("입력한 검색어 : " + searchKeyword);

					printArticles = new ArrayList<>();

					for (Article article : this.articles) {
						if (article.getTitle().contains(searchKeyword)) {
							printArticles.add(article);
						}
					}

					if (printArticles.size() == 0) {
						System.out.println("검색결과가 없습니다");
						continue;
					}
				}

				System.out.println("번호	|	제목	|		작성일");
				for (int i = printArticles.size() - 1; i >= 0; i--) {
					Article article = printArticles.get(i);
					System.out.printf("%d	|	%s	|	%s\n", article.getId(), article.getTitle(),
							article.getUpdateDate());
				}
			} else if (cmd.startsWith("article detail ")) {
				int id = this.getCmdNum(cmd);

				if (id == -1) {
					System.out.println("게시글 번호를 정확하게 입력해주세요");
					continue;
				}

				Article foundArticle = this.getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시글이 존재하지 않습니다\n", id);
					continue;
				}

				System.out.printf("== %d번 게시글 상세보기 ==\n", id);
				System.out.printf("번호 : %d\n", foundArticle.getId());
				System.out.printf("작성일 : %s\n", foundArticle.getRegDate());
				System.out.printf("수정일 : %s\n", foundArticle.getUpdateDate());
				System.out.printf("제목 : %s\n", foundArticle.getTitle());
				System.out.printf("내용 : %s\n", foundArticle.getBody());
			} else if (cmd.startsWith("article modify ")) {
				int id = this.getCmdNum(cmd);

				if (id == -1) {
					System.out.println("게시글 번호를 정확하게 입력해주세요");
					continue;
				}

				Article foundArticle = this.getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시글이 존재하지 않습니다\n", id);
					continue;
				}

				System.out.printf("수정할 제목 : ");
				String title = sc.nextLine().trim();
				System.out.printf("수정할 내용 : ");
				String body = sc.nextLine().trim();

				foundArticle.setTitle(title);
				foundArticle.setBody(body);
				foundArticle.setUpdateDate(Util.getDateStr());

				System.out.printf("%d번 게시글의 수정이 완료되었습니다\n", id);
			} else if (cmd.startsWith("article delete ")) {
				int id = this.getCmdNum(cmd);

				if (id == -1) {
					System.out.println("게시글 번호를 정확하게 입력해주세요");
					continue;
				}

				Article foundArticle = this.getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시글이 존재하지 않습니다\n", id);
					continue;
				}

				this.articles.remove(foundArticle);

				System.out.printf("%d번 게시글을 삭제했습니다\n", id);
			} else {
				System.out.println("존재하지 않는 명령어 입니다");
			}
		}
		System.out.println("== 프로그램 종료 ==");
		sc.close();
	}

	private Article getArticleById(int id) {
		for (Article article : this.articles) {
			if (id == article.getId()) {
				return article;
			}
		}
		return null;
	}

	private int getCmdNum(String cmd) {
		String[] cmdBits = cmd.split(" ");

		try {
			return Integer.parseInt(cmdBits[2]);
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		} catch (NumberFormatException e) {
			return -1;
		} catch (Exception e) {
			return -1;
		}
	}
}
