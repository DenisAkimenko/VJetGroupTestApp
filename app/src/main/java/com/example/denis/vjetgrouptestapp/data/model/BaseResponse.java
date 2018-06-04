package com.example.denis.vjetgrouptestapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseResponse {

	@SerializedName("totalResults")
	private int totalResults;
	@SerializedName("articles")
	private List<Article> articles;
	@SerializedName("sources")
	private List<Source> sources;
	@SerializedName("status")
	private String status;

	public BaseResponse(int totalResults, List<Article> articles, List<Source> sources, String status) {
		this.totalResults = totalResults;
		this.articles = articles;
		this.sources = sources;
		this.status = status;
	}

	public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

	public int getTotalResults(){
		return totalResults;
	}

	public void setArticles(List<Article> articles){
		this.articles = articles;
	}

	public List<Article> getArticles(){
		return articles;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public List<Source> getSources() {
		return sources;
	}

	public void setSources(List<Source> sources) {
		this.sources = sources;
	}
}