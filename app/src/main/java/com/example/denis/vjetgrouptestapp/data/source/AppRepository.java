package com.example.denis.vjetgrouptestapp.data.source;

import com.example.denis.vjetgrouptestapp.data.model.Article;
import com.example.denis.vjetgrouptestapp.data.model.BaseResponse;
import com.example.denis.vjetgrouptestapp.data.source.local.AppLocalDataSource;
import com.example.denis.vjetgrouptestapp.data.source.remote.AppRemoteDataSource;

import java.util.List;

import io.reactivex.Single;

public class AppRepository implements DataSource {

    private static AppRepository INSTANCE;

    private DataSource mRemoteDataSource;
    private DataSource mLocalDataSource;

    private AppRepository() {
        mRemoteDataSource = AppRemoteDataSource.getInstance();
        mLocalDataSource = AppLocalDataSource.getInstance();
    }

    public static AppRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppRepository();
        }
        return INSTANCE;
    }

    @Override
    public Single<BaseResponse> getArticles(SearchProperties searchProperties) {
        return mRemoteDataSource.getArticles(searchProperties);
    }

    @Override
    public Single<BaseResponse> getTopHeadlines() {
        return mRemoteDataSource.getTopHeadlines();
    }

    @Override
    public Single<BaseResponse> getSources() {
        return mRemoteDataSource.getSources();
    }

    @Override
    public void addToFavorites(Article article) {
        mLocalDataSource.addToFavorites(article);
    }

    @Override
    public void removeFromFavorites(Article article) {
        mLocalDataSource.removeFromFavorites(article);
    }

    @Override
    public List<Article> getFavorites() {
        return mLocalDataSource.getFavorites();
    }
}
