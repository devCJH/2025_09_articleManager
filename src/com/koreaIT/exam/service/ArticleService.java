package com.koreaIT.exam.service;

import com.koreaIT.exam.dao.ArticleDao;

public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = new ArticleDao();
	}
	
	public int writeArticle(String regDate, String updateDate, String title, String body) {
		return this.articleDao.writeArticle(regDate, updateDate, title, body);
	}

	public void makeTestData() {
		this.articleDao.makeTestData();
	}

}
