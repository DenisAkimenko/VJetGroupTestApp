package com.example.denis.vjetgrouptestapp.ui.favoritearticles;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.denis.vjetgrouptestapp.R;
import com.example.denis.vjetgrouptestapp.data.model.Article;
import com.example.denis.vjetgrouptestapp.ui.adapters.ArticlesRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

public class FavoriteArticlesFragment extends Fragment implements FavoriteArticlesContract.View,
        ArticlesRecyclerAdapter.ArticleItemListener {

    private FavoriteArticlesContract.Presenter mPresenter;

    private ProgressBar mProgressBar;
    private ArticlesRecyclerAdapter mArticlesRecyclerAdapter;
    private View mView;

    public FavoriteArticlesFragment() {

    }

    public static FavoriteArticlesFragment newInstance() {
        return new FavoriteArticlesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_favorite_articles, container, false);

        mProgressBar = mView.findViewById(R.id.progressBar);
        RecyclerView rvArticles = mView.findViewById(R.id.rv_favorite_articles);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvArticles.setLayoutManager(linearLayoutManager);

        rvArticles.addItemDecoration(new DividerItemDecoration(rvArticles.getContext(),
                linearLayoutManager.getOrientation()));

        mArticlesRecyclerAdapter = new ArticlesRecyclerAdapter(getContext(),
                new ArrayList<>(),this,true);
        rvArticles.setAdapter(mArticlesRecyclerAdapter);

        return mView;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (menuVisible) {
            if (mPresenter != null) {
                mPresenter.subscribe();
            }
        }
    }

    @Override
    public void setPresenter(FavoriteArticlesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showFavoriteArticles(List<Article> articles) {
        mArticlesRecyclerAdapter.setArticlesList(articles);
    }

    @Override
    public void showProgress(boolean isVisible) {
        mProgressBar.setVisibility(isVisible? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError() {
        Snackbar.make(mView, R.string.error_message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void changeFavoriteStatus(Article favoriteArticle) {
        mPresenter.removeFromFavorites(favoriteArticle);
    }

    @Override
    public void onDestroyView() {
        mPresenter.unsubscribe();
        super.onDestroyView();
    }

    @Override
    public void shareOnFacebook(Article article) { }
}
