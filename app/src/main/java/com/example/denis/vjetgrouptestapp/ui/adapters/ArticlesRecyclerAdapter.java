package com.example.denis.vjetgrouptestapp.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.denis.vjetgrouptestapp.R;
import com.example.denis.vjetgrouptestapp.data.model.Article;
import com.example.denis.vjetgrouptestapp.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticlesRecyclerAdapter extends RecyclerView.Adapter<ArticlesRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<Article> mArticles;
    private ArticleItemListener mItemListener;
    private boolean mIsFavorites;

    public ArticlesRecyclerAdapter(Context context,
                                   List<Article> articles,
                                   ArticleItemListener itemListener,
                                   boolean isFavorites) {
        mContext = context;
        mArticles = articles;
        mItemListener = itemListener;
        mIsFavorites = isFavorites;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article, parent, false);

        return new ArticlesRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleView.setText(mArticles.get(position).getTitle());
        holder.authorView.setText(mArticles.get(position).getAuthor());
        holder.dateView.setText(Utils.formateDate(mArticles.get(position).getPublishedAt()));

        if (mIsFavorites) {
            holder.favoriteView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_delete_black_24dp));
            holder.shareButton.setVisibility(View.GONE);
        }

        if(mArticles.get(position).getUrlToImage() != null && !mArticles.get(position).getUrlToImage().isEmpty()) {
            Picasso.with(mContext)
                    .load(mArticles.get(position).getUrlToImage())
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public void setArticlesList(List<Article> articles) {
        mArticles = articles;
        notifyDataSetChanged();
    }

    public void addData(List<Article> newArticles) {
        if (mArticles != null) {
            mArticles.addAll(newArticles);
        }
        notifyDataSetChanged();
    }

    public void clearData() {
        mArticles.clear();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private ImageButton favoriteView;
        private TextView titleView;
        private TextView authorView;
        private TextView dateView;
        private Button shareButton;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.card_avatar);
            titleView = view.findViewById(R.id.card_title);
            authorView = view.findViewById(R.id.card_author);
            dateView = view.findViewById(R.id.list_date);
            shareButton = view.findViewById(R.id.share_button);
            favoriteView = view.findViewById(R.id.favourite_button);

            shareButton.setOnClickListener(v -> {
                if (mItemListener != null) {
                    mItemListener.shareOnFacebook(mArticles.get(getAdapterPosition()));
                    notifyDataSetChanged();
                }
            });

            favoriteView.setOnClickListener(v -> {
                if (mItemListener != null) {
                    mItemListener.changeFavoriteStatus(mArticles.get(getAdapterPosition()));
                    notifyDataSetChanged();
                }
            });
        }
    }

    public interface ArticleItemListener {

        void changeFavoriteStatus(Article favoriteArticle);

        void shareOnFacebook(Article article);
    }
}
