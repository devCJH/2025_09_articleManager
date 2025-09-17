package com.koreaIT.exam.container;

import com.koreaIT.exam.dao.ArticleDao;
import com.koreaIT.exam.dao.MemberDao;
import com.koreaIT.exam.service.ArticleService;
import com.koreaIT.exam.service.MemberService;

public class Container {
	public static MemberDao memberDao;
	public static MemberService memberService;
	public static ArticleDao articleDao;
	public static ArticleService articleService;
	
	static {
		memberDao = new MemberDao();
		memberService = new MemberService();
		articleDao = new ArticleDao();
		articleService = new ArticleService();
	}
}
