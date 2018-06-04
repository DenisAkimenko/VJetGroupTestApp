package com.example.denis.vjetgrouptestapp.ui.favoritearticles;

import com.example.denis.vjetgrouptestapp.base.BasePresenter;
import com.example.denis.vjetgrouptestapp.base.BaseView;
import com.example.denis.vjetgrouptestapp.data.model.Article;

import java.util.List;

public interface FavoriteArticlesContract {

    interface View extends BaseView<Presenter> {

        void showFavoriteArticles(List<Article> articles);

        void showProgress(boolean isVisible);

        void showError();
    }

    interface Presenter extends BasePresenter {

        void fetchFavoriteArticles();

        void removeFromFavorites(Article article);
    }
}
