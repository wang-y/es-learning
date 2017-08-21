package com.wanysoul.es.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "projectname", type = "article", shards = 5, replicas = 1)
public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2745731437856912615L;

	@Id
	private Long id;

	/** 标题 */
	private String title;

	/** 摘要 */
	private String abstracts;

	/** 内容 */
	private String content;

	/** 发表时间 */
	private Date postTime;

	/** 点击率 */
	private Long clickCount;

	/** 作者 */
	private String author;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public Long getClickCount() {
		return clickCount;
	}

	public void setClickCount(Long clickCount) {
		this.clickCount = clickCount;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
