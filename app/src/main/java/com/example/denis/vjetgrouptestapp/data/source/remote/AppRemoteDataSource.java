package com.example.denis.vjetgrouptestapp.data.source.remote;

import com.example.denis.vjetgrouptestapp.data.constants.ApiConstants;
import com.example.denis.vjetgrouptestapp.data.model.Article;
import com.example.denis.vjetgrouptestapp.data.model.BaseResponse;
import com.example.denis.vjetgrouptestapp.data.source.DataSource;
import com.example.denis.vjetgrouptestapp.data.source.SearchProperties;
import com.example.denis.vjetgrouptestapp.data.source.remote.retrofit.ApiManager;

import java.util.List;

import io.reactivex.Single;

public class AppRemoteDataSource implements DataSource {

    private static AppRemoteDataSource INSTANCE;
    private static ApiManager sApiManager;

    private AppRemoteDataSource() {
        sApiManager = ApiManager.getInstance();
    }

    public static AppRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (AppRemoteDataSource.class) {
                if (INSTANCE == null)
                    INSTANCE = new AppRemoteDataSource();
            }
        }
        return INSTANCE;
    }

    @Override
    public Single<BaseResponse> getArticles(SearchProperties searchProperties) {
        return sApiManager.getService().getArticles(ApiConstants.API_KEY,
                searchProperties.getSortBy(),
                searchProperties.getStringOfSources(),
                searchProperties.getFromDate(),
                searchProperties.getToDate(),
                searchProperties.getPage(),
                searchProperties.getPageSize());
    }

    @Override
    public Single<BaseResponse> getTopHeadlines() {
        return sApiManager.getService().getTopHeadlines(ApiConstants.API_KEY, ApiConstants.DEFAULT_LANGUAGE);
    }

    @Override
    public Single<BaseResponse> getSources() {
        return sApiManager.getService().getSources(ApiConstants.API_KEY, ApiConstants.DEFAULT_LANGUAGE);
    }

    @Override
    public void addToFavorites(Article article) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeFromFavorites(Article article) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Article> getFavorites() {
        throw new UnsupportedOperationException();
    }

}
