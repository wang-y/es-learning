package com.wanysoul.es;

import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanysoul.es.dao.ArticleDao;
import com.wanysoul.es.model.Article;

@RestController
public class ArticleController {

	@Resource
	private ArticleDao articleDao;

	@RequestMapping("/add")
	public void testSaveArticleIndex() {

		Article article = new Article();
		article.setId(1L);
		article.setTitle("springboot integreate elasticsearch");
		article.setAbstracts("springboot integreate elasticsearch is very easy");
		article.setAuthor("wy");
		article.setContent("elasticsearch based on lucene," + "spring-data-elastichsearch based on elaticsearch"
				+ ",this tutorial tell you how to integrete springboot with spring-data-elasticsearch");
		article.setPostTime(new Date());
		article.setClickCount(1L);

		articleDao.save(article);
	}

	@RequestMapping("/query")
	public void testSearch() {
		String queryString = "springboot";// 搜索关键字
		QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
		Iterable<Article> searchResult = articleDao.search(builder);
		Iterator<Article> iterator = searchResult.iterator();
		while (iterator.hasNext()) {
			Article next = iterator.next();
			System.out.println(next.getAuthor());
		}
	}
}
