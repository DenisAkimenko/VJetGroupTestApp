package com.example.denis.vjetgrouptestapp.ui.articles;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.denis.vjetgrouptestapp.data.model.Article;
import com.example.denis.vjetgrouptestapp.data.model.Source;
import com.example.denis.vjetgrouptestapp.data.source.AppRepository;
import com.example.denis.vjetgrouptestapp.data.source.DataSource;
import com.example.denis.vjetgrouptestapp.data.source.SearchProperties;
import com.example.denis.vjetgrouptestapp.utils.rx.AppSchedulerProvider;
import com.example.denis.vjetgrouptestapp.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class ArticlesPresenter implements ArticlesContract.Presenter {

    private ArticlesContract.View mView;
    private SchedulerProvider mSchedulerProvider;
    private DataSource mRepository;
    private List<Article> mArticles;
    private List<Source> mSources;

    public ArticlesPresenter(ArticlesContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mArticles = new ArrayList<>();
        mSources = new ArrayList<>();
        mSchedulerProvider = new AppSchedulerProvider();
        mRepository = AppRepository.getInstance();
    }

    @Override
    public void subscribe() {
        fetchTopHeadlines();
        fetchSources();
    }

    @Override
    public void unsubscribe() {
        mView = null;
        mRepository = null;
        mArticles = null;
    }

    @SuppressLint("CheckResult")
    @Override
    public void fetchArticlesList(SearchProperties searchProperties) {
        mRepository.getArticles(searchProperties)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(
                        it -> {
                            mArticles = it.getArticles();
                            mView.addArticles(mArticles);
                            mView.showProgress(false);
                        },
                        error -> {
                            Log.e("denisLogs", error.getMessage());
                            mView.showProgress(false);
                        });
    }

    @SuppressLint("CheckResult")
    @Override
    public void fetchTopHeadlines() {
        if (mArticles != null) {
            mRepository.getTopHeadlines()
                    .subscribeOn(mSchedulerProvider.io())
                    .observeOn(mSchedulerProvider.ui())
                    .subscribe(
                            it -> {
                                mArticles = it.getArticles();
                                mView.addArticles(mArticles);
                                mView.showProgress(false);
                            },
                            error -> {
                                Log.e("denisLogs", error.getMessage());
                                mView.showProgress(false);
                            });
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void fetchSources() {
        if (mSources != null) {
            mRepository.getSources()
                    .subscribeOn(mSchedulerProvider.io())
                    .observeOn(mSchedulerProvider.ui())
                    .subscribe(
                            it -> {
                                mSources = it.getSources();
                                mView.onSearchReady(mSources);
                                mView.showProgress(false);
                            },
                            error -> {
                                Log.e("denisLogs", error.getMessage());
                                mView.showProgress(false);
                            });
        }
    }

    @Override
    public void addToFavourites(Article article) {
        mRepository.addToFavorites(article);
    }
}
