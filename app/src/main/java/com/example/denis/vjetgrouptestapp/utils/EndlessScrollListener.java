package com.example.denis.vjetgrouptestapp.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private int previousTotalItemCount = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private int startingPageIndex = 0;
    private int currentPage = 0;

    @Override
    public void onScrolled(RecyclerView mRecyclerView, int dx, int dy) {
        super.onScrolled(mRecyclerView, dx, dy);
        LinearLayoutManager mLayoutManager = (LinearLayoutManager) mRecyclerView
                .getLayoutManager();

        visibleItemCount = mRecyclerView.getChildCount();
        totalItemCount = mLayoutManager.getItemCount();
        firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
        onScroll(firstVisibleItem, visibleItemCount, totalItemCount);
    }

    private void onScroll(int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }

        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
            currentPage++;
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem +
                visibleThreshold)) {
            onLoadMore(currentPage + 1, totalItemCount);
            loading = true;
        }
    }

    public abstract void onLoadMore(int page, int totalItemsCount);
}
