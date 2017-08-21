package com.wanysoul.es.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.wanysoul.es.model.Article;

public interface ArticleDao extends ElasticsearchRepository<Article, Long> {

}
