package com.rais.haidar.applenewsapi.Model;

import com.google.gson.annotations.SerializedName;

public class ArticlesItem{

	@SerializedName("publishedAt")
	private String publishedAt;

	@SerializedName("author")
	private String author;

	@SerializedName("urlToImage")
	private String urlToImage;

	@SerializedName("description")
	private String description;

	@SerializedName("source")
	private Source source;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	public void setPublishedAt(String publishedAt){
		this.publishedAt = publishedAt;
	}

	public String getPublishedAt(){
		return publishedAt;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return author;
	}

	public void setUrlToImage(String urlToImage){
		this.urlToImage = urlToImage;
	}

	public String getUrlToImage(){
		return urlToImage;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setSource(Source source){
		this.source = source;
	}

	public Source getSource(){
		return source;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"ArticlesItem{" + 
			"publishedAt = '" + publishedAt + '\'' + 
			",author = '" + author + '\'' + 
			",urlToImage = '" + urlToImage + '\'' + 
			",description = '" + description + '\'' + 
			",source = '" + source + '\'' + 
			",title = '" + title + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}