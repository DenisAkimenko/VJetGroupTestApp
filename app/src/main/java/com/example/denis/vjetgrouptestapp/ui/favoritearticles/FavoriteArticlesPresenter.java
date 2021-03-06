package com.example.denis.vjetgrouptestapp.ui.favoritearticles;

import android.annotation.SuppressLint;

import com.example.denis.vjetgrouptestapp.data.model.Article;
import com.example.denis.vjetgrouptestapp.data.source.AppRepository;
import com.example.denis.vjetgrouptestapp.data.source.DataSource;
import com.example.denis.vjetgrouptestapp.utils.rx.AppSchedulerProvider;
import com.example.denis.vjetgrouptestapp.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class FavoriteArticlesPresenter implements FavoriteArticlesContract.Presenter {

    private FavoriteArticlesContract.View mView;
    private DataSource mRepository;
    private SchedulerProvider mSchedulerProvider;
    private List<Article> mArticles;

    public FavoriteArticlesPresenter(FavoriteArticlesContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mArticles = new ArrayList<>();
        mSchedulerProvider = new AppSchedulerProvider();
        mRepository = AppRepository.getInstance();
    }

    @Override
    public void subscribe() {
        fetchFavoriteArticles();
    }

    @Override
    public void unsubscribe() {
        mView = null;
        mRepository = null;
        mArticles = null;
    }

    @SuppressLint("CheckResult")
    @Override
    public void fetchFavoriteArticles() {
        mArticles = mRepository.getFavorites();
        mView.showFavoriteArticles(mArticles);
        mView.showProgress(false);
    }

    @Override
    public void removeFromFavorites(Article article) {
        mRepository.removeFromFavorites(article);
    }
}
