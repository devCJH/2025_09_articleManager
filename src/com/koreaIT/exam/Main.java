package com.koreaIT.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.exam.util.Util;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		int lastArticleId = 0;
		List<Article> articles = new ArrayList<>();
		
		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();
			
			if (cmd.equals("exit")) {
				break;
			}
			
			if (cmd.trim().length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}
			
			if (cmd.equals("article write")) {
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				lastArticleId++;
				
				Article article = new Article(lastArticleId, Util.getDateStr(), Util.getDateStr(), title, body);
				articles.add(article);
				
				System.out.println(lastArticleId + "번 게시글이 작성되었습니다");
			} else if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 존재하지 않습니다");
					continue;
				}
				
				System.out.println("번호	|	제목	|		작성일");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d	|	%s	|	%s\n", article.id, article.title, article.updateDate);
				}
			} else if (cmd.startsWith("article detail ")) {
				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);
				
				Article foundArticle = null;
				
				for (Article article : articles) {
					if (id == article.id) {
						foundArticle = article;
						break;
					}
				}
				
				if (foundArticle == null) {
					System.out.printf("%d번 게시글이 존재하지 않습니다\n", id);
					continue;
				}
				
				System.out.printf("== %d번 게시글 상세보기 ==\n", id);
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("작성일 : %s\n", foundArticle.regDate);
				System.out.printf("수정일 : %s\n", foundArticle.updateDate);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
			} else if (cmd.startsWith("article modify ")) {
				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);
				
				Article foundArticle = null;
				
				for (Article article : articles) {
					if (id == article.id) {
						foundArticle = article;
						break;
					}
				}
				
				if (foundArticle == null) {
					System.out.printf("%d번 게시글이 존재하지 않습니다\n", id);
					continue;
				}
				
				System.out.printf("수정할 제목 : ");
				String title = sc.nextLine();
				System.out.printf("수정할 내용 : ");
				String body = sc.nextLine();
				
				foundArticle.title = title;
				foundArticle.body = body;
				foundArticle.updateDate = Util.getDateStr();
				
				System.out.printf("%d번 게시글의 수정이 완료되었습니다\n", id);
			} else if (cmd.startsWith("article delete ")) {
				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);
				
				int foundIndex = -1;
				
				int i = 0;
				for (Article article : articles) {
					if (id == article.id) {
						foundIndex = i;
						break;
					}
					i++;
				}
				
//				for (int i = 0; i < articles.size(); i++) {
//					Article article = articles.get(i);
//					if (id == article.id) {
//						foundIndex = i;
//						break;
//					}
//				}
				
				if (foundIndex == -1) {
					System.out.printf("%d번 게시글이 존재하지 않습니다\n", id);
					continue;
				}
				
				articles.remove(foundIndex);
				
				System.out.printf("%d번 게시글을 삭제했습니다\n", id);
			} else {
				System.out.println("존재하지 않는 명령어 입니다");
			}
		}
		System.out.println("== 프로그램 종료 ==");
		sc.close();
	}
}

class Article {
	int id;
	String regDate;
	String updateDate;
	String title;
	String body;
	
	public Article(int id, String regDate, String updateDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
	}
}