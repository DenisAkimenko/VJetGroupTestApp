package com.example.denis.vjetgrouptestapp.data.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Source extends RealmObject {

	@SerializedName("id")
	private String id;
	@SerializedName("name")
	private String name;
	@SerializedName("description")
	private String description;
	@SerializedName("url")
	private String url;
	@SerializedName("category")
	private String category;
	@SerializedName("language")
	private String language;
	@SerializedName("country")
	private String country;

	private boolean isSelected = false;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean selected) {
		isSelected = selected;
	}
}