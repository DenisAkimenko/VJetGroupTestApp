package com.example.denis.vjetgrouptestapp.ui.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.denis.vjetgrouptestapp.R;
import com.example.denis.vjetgrouptestapp.ui.articles.ArticlesFragment;
import com.example.denis.vjetgrouptestapp.ui.favoritearticles.FavoriteArticlesFragment;

public class TabsViewPagerAdapter extends FragmentPagerAdapter {

    private static final int ARTICLES_TAB_POSITION = 0;
    private static final int FAVOURITE_ARTICLES_TAB_POSITION = 1;
    private static final int COUNT_ITEMS = 2;

    private ArticlesFragment mArticlesFragment;
    private FavoriteArticlesFragment mFavoriteArticlesFragment;

    private Context mContext;

    public TabsViewPagerAdapter(FragmentManager fragmentManager,
                                ArticlesFragment articlesFragment,
                                FavoriteArticlesFragment favoriteArticlesFragment,
                                Context context) {
        super(fragmentManager);
        mArticlesFragment = articlesFragment;
        mFavoriteArticlesFragment = favoriteArticlesFragment;
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case ARTICLES_TAB_POSITION:
                return mArticlesFragment;
            case FAVOURITE_ARTICLES_TAB_POSITION:
                return mFavoriteArticlesFragment;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case ARTICLES_TAB_POSITION:
                return mContext.getString(R.string.frag_articles_title);
            case FAVOURITE_ARTICLES_TAB_POSITION:
                return mContext.getString(R.string.frag_favorite_articles_title);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return COUNT_ITEMS;
    }
}

