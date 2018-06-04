package com.example.denis.vjetgrouptestapp.data.source;

import com.example.denis.vjetgrouptestapp.data.model.Article;
import com.example.denis.vjetgrouptestapp.data.model.BaseResponse;

import java.util.List;

import io.reactivex.Single;

public interface DataSource {

    Single<BaseResponse> getArticles(SearchProperties searchProperties);

    Single<BaseResponse> getTopHeadlines();

    Single<BaseResponse> getSources();

    void addToFavorites(Article article);

    void removeFromFavorites(Article article);

    List<Article> getFavorites();
}
