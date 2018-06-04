package com.example.denis.vjetgrouptestapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.denis.vjetgrouptestapp.R;
import com.example.denis.vjetgrouptestapp.ui.adapters.TabsViewPagerAdapter;
import com.example.denis.vjetgrouptestapp.ui.articles.ArticlesFragment;
import com.example.denis.vjetgrouptestapp.ui.articles.ArticlesPresenter;
import com.example.denis.vjetgrouptestapp.ui.favoritearticles.FavoriteArticlesFragment;
import com.example.denis.vjetgrouptestapp.ui.favoritearticles.FavoriteArticlesPresenter;

public class MainFragment extends Fragment implements MainContract.View {

    private MainContract.Presenter mPresenter;

    private ViewPager mTabsViewPager;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mTabsViewPager = root.findViewById(R.id.view_pager);
        MainActivity mainActivity = ((MainActivity) getActivity());
        if (mainActivity != null) {
            mainActivity.setupToolbarWithViewPager(mTabsViewPager);
        }

        mPresenter.subscribe();
        init();
        return root;
    }

    void init() {
        ArticlesFragment articlesFragment = ArticlesFragment.newInstance();
        FavoriteArticlesFragment favoriteArticlesFragment = FavoriteArticlesFragment.newInstance();

        TabsViewPagerAdapter tabsAdapter = new TabsViewPagerAdapter(getChildFragmentManager(),
                articlesFragment,
                favoriteArticlesFragment,
                getContext());

        new ArticlesPresenter(articlesFragment);
        new FavoriteArticlesPresenter(favoriteArticlesFragment);

        mTabsViewPager.setAdapter(tabsAdapter);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
