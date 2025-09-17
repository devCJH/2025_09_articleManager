package com.koreaIT.exam.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.exam.dto.Article;
import com.koreaIT.exam.util.Util;

public class ArticleDao {

	private int lastArticleId;
	private List<Article> articles;

	public ArticleDao() {
		this.lastArticleId = 0;
		this.articles = new ArrayList<>();
	}

	public int writeArticle(String regDate, String updateDate, int memberId, String title, String body) {
		this.lastArticleId++;

		Article article = new Article(this.lastArticleId, regDate, updateDate, memberId, title, body);
		this.articles.add(article);

		return this.lastArticleId;
	}

	public List<Article> getArticles() {
		return this.articles;
	}

	public List<Article> getPrintArticles(String searchKeyword) {
		List<Article> printArticles = new ArrayList<>();

		for (Article article : this.articles) {
			if (article.getTitle().contains(searchKeyword)) {
				printArticles.add(article);
			}
		}
		return printArticles;
	}

	public Article getArticleById(int id) {
		for (Article article : this.articles) {
			if (id == article.getId()) {
				return article;
			}
		}
		return null;
	}

	public void modifyArticle(Article foundArticle, String title, String body, String updateDate) {
		foundArticle.setTitle(title);
		foundArticle.setBody(body);
		foundArticle.setUpdateDate(updateDate);
	}

	public void deleteArticle(Article foundArticle) {
		this.articles.remove(foundArticle);
	}

	public void makeTestData() {
		for (int i = 1; i <= 5; i++) {
			this.lastArticleId++;
			this.articles
					.add(new Article(this.lastArticleId, Util.getDateStr(), Util.getDateStr(), (int) (Math.random() * 3 + 1), "제목" + i, "내용" + i));
		}
	}
}
