package com.koreaIT.exam.service;

import java.util.List;

import com.koreaIT.exam.container.Container;
import com.koreaIT.exam.dao.ArticleDao;
import com.koreaIT.exam.dto.Article;

public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = Container.articleDao;
	}
	
	public int writeArticle(String regDate, String updateDate, int memberId, String title, String body) {
		return this.articleDao.writeArticle(regDate, updateDate, memberId, title, body);
	}

	public List<Article> getArticles() {
		return this.articleDao.getArticles();
	}
	
	public List<Article> getPrintArticles(String searchKeyword) {
		return this.articleDao.getPrintArticles(searchKeyword);
	}

	public String getSearchKeywordByCmd(String cmd) {
		return cmd.substring("article list".length()).trim();
	}
	
	public Article getArticleById(int id) {
		return this.articleDao.getArticleById(id);
	}
	
	public void modifyArticle(Article foundArticle, String title, String body, String updateDate) {
		this.articleDao.modifyArticle(foundArticle, title, body, updateDate);
	}

	public void deleteArticle(Article foundArticle) {
		this.articleDao.deleteArticle(foundArticle);
	}
	
	public void makeTestData() {
		this.articleDao.makeTestData();
	}
	
	public int getCmdNum(String cmd) {
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
