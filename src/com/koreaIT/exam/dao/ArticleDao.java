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
	
	public int writeArticle(String regDate, String updateDate, String title, String body) {
		this.lastArticleId++;

		Article article = new Article(this.lastArticleId, regDate, updateDate, title, body);
		this.articles.add(article);
		
		return this.lastArticleId;
	}
	
	public void makeTestData() {
		for (int i = 1; i <= 5; i++) {
			this.lastArticleId++;
			this.articles.add(new Article(this.lastArticleId, Util.getDateStr(), Util.getDateStr(), "제목" + i, "내용" + i));
		}
	}
}
