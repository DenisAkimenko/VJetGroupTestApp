package com.example.denis.vjetgrouptestapp.ui.articles;

import com.example.denis.vjetgrouptestapp.base.BasePresenter;
import com.example.denis.vjetgrouptestapp.base.BaseView;
import com.example.denis.vjetgrouptestapp.data.model.Article;
import com.example.denis.vjetgrouptestapp.data.model.Source;
import com.example.denis.vjetgrouptestapp.data.source.SearchProperties;

import java.util.List;

public interface ArticlesContract {

    interface View extends BaseView<Presenter> {

        void onSearchReady(List<Source> sources);

        void addArticles(List<Article> articles);

        void showProgress(boolean isVisible);
    }

    interface Presenter extends BasePresenter {

        void fetchArticlesList(SearchProperties searchProperties);

        void fetchTopHeadlines();

        void fetchSources();

        void addToFavourites(Article article);
    }
}
