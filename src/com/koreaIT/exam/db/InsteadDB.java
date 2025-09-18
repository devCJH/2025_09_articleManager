package com.koreaIT.exam.db;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.exam.dto.Article;
import com.koreaIT.exam.dto.Member;

public class InsteadDB {
	public static List<Article> articles;
	public static List<Member> members;
	
	static {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}
}
