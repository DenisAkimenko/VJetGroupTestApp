package com.example.denis.vjetgrouptestapp.ui.articles;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.denis.vjetgrouptestapp.R;
import com.example.denis.vjetgrouptestapp.data.model.Article;
import com.example.denis.vjetgrouptestapp.data.model.Source;
import com.example.denis.vjetgrouptestapp.data.source.SearchProperties;
import com.example.denis.vjetgrouptestapp.ui.adapters.ArticlesRecyclerAdapter;
import com.example.denis.vjetgrouptestapp.ui.dialog.DialogSearchCallback;
import com.example.denis.vjetgrouptestapp.ui.dialog.DialogSearchProperties;
import com.example.denis.vjetgrouptestapp.utils.EndlessScrollListener;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;
import java.util.List;

public class ArticlesFragment extends Fragment implements ArticlesContract.View,
        ArticlesRecyclerAdapter.ArticleItemListener, DialogSearchCallback, View.OnClickListener {

    private ArticlesContract.Presenter mPresenter;

    private ProgressBar mProgressBar;
    private ArticlesRecyclerAdapter mArticlesRecyclerAdapter;
    private View mView;

    private DialogSearchProperties mSearchDialog;
    private SearchProperties mLastProperties;
    private ShareDialog mFacebookShareDialog;

    public ArticlesFragment() { }

    public static ArticlesFragment newInstance() {
        return new ArticlesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_articles, container, false);
        initViews();
        return mView;
    }

    private void initViews() {
        setHasOptionsMenu(true);
        RecyclerView rvArticles = mView.findViewById(R.id.rv_articles);
        mProgressBar = mView.findViewById(R.id.progressBar);
        (mView.findViewById(R.id.fab_search)).setOnClickListener(this);

        mArticlesRecyclerAdapter = new ArticlesRecyclerAdapter(getContext(),
                new ArrayList<>(),this,false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvArticles.setLayoutManager(linearLayoutManager);
        rvArticles.addItemDecoration(new DividerItemDecoration(rvArticles.getContext(),
                linearLayoutManager.getOrientation()));
        rvArticles.setAdapter(mArticlesRecyclerAdapter);
        rvArticles.addOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (mLastProperties != null) {
                    mLastProperties.setPage(page);
                    mPresenter.fetchArticlesList(mLastProperties);
                }
            }
        });

        mSearchDialog = new DialogSearchProperties(getContext(), this);

        mFacebookShareDialog = new ShareDialog(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.subscribe();
        }
    }

    @Override
    public void setPresenter(ArticlesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onSearchReady(List<Source> sources) {
        mSearchDialog.setSources(sources);
    }

    @Override
    public void addArticles(List<Article> articles) {
        mArticlesRecyclerAdapter.addData(articles);
    }

    @Override
    public void changeFavoriteStatus(Article favoriteArticle) {
        mPresenter.addToFavourites(favoriteArticle);
    }

    @Override
    public void shareOnFacebook(Article article) {
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse(article.getUrl()))
                    .setQuote(article.getTitle())
                    .build();
            mFacebookShareDialog.show(content);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse(getString(R.string.facebook_app_id)))
                    .setQuote(getString(R.string.invite_string))
                    .build();
            mFacebookShareDialog.show(content);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress(boolean isVisible) {
        mProgressBar.setVisibility(isVisible? View.VISIBLE : View.GONE);
    }

    @Override
    public void onSearch(SearchProperties properties) {
        mLastProperties = properties;
        mArticlesRecyclerAdapter.clearData();
        mPresenter.fetchArticlesList(properties);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unsubscribe();
    }

    @Override
    public void onClick(View v) {
        mSearchDialog.show();
    }
}
