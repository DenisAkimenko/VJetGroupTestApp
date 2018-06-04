package com.example.denis.vjetgrouptestapp.data.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Article extends RealmObject {

	@SerializedName("author")
	private String author;
	@SerializedName("title")
	private String title;
	@SerializedName("description")
	private String description;
	@SerializedName("publishedAt")
	private String publishedAt;
	@SerializedName("urlToImage")
	private String urlToImage;
	@SerializedName("source")
	private Source source;
	@PrimaryKey
	@SerializedName("url")
	private String url;

	private boolean isFavorite = false;

	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getPublishedAt() {
		return publishedAt;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Source getSource() {
		return source;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean favorite) {
		isFavorite = favorite;
	}

}