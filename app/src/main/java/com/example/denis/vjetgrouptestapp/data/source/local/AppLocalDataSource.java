package com.example.denis.vjetgrouptestapp.data.source.local;

import android.support.annotation.Nullable;
import android.util.Log;

import com.example.denis.vjetgrouptestapp.data.model.Article;
import com.example.denis.vjetgrouptestapp.data.model.BaseResponse;
import com.example.denis.vjetgrouptestapp.data.source.DataSource;
import com.example.denis.vjetgrouptestapp.data.source.SearchProperties;

import java.util.List;

import io.reactivex.Single;
import io.realm.Realm;
import io.realm.exceptions.RealmMigrationNeededException;

public class AppLocalDataSource implements DataSource {

    @Nullable
    private static AppLocalDataSource INSTANCE;

    private AppLocalDataSource() { }

    public static AppLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Single<BaseResponse> getArticles(SearchProperties searchProperties) {
        return Single.create(it -> {
            Realm realm = Realm.getDefaultInstance();
            List<Article> articles = realm.where(Article.class).findAll();
            realm.copyFromRealm(articles);
            realm.close();
        });
    }

    @Override
    public void addToFavorites(Article article) {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(realm1 -> realm.copyToRealmOrUpdate(article));
        } catch (IllegalArgumentException | RealmMigrationNeededException e) {
            Log.e("Error", e.getMessage());
        }
    }

    @Override
    public void removeFromFavorites(Article article) {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(realm1 -> realm.where(Article.class)
                    .equalTo("url",article.getUrl()).findFirst().deleteFromRealm());
        } catch (IllegalArgumentException | RealmMigrationNeededException | NullPointerException e) {
            Log.e("Error", e.getMessage());
        }
    }

    @Override
    public List<Article> getFavorites() {
        Realm realm = Realm.getDefaultInstance();
        List<Article> articles = realm.where(Article.class).findAll();
        if (articles != null && articles.size() != 0) {
            realm.copyFromRealm(articles);
        }
        return articles;
    }

    @Override
    public Single<BaseResponse> getTopHeadlines() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Single<BaseResponse> getSources() {
        throw new UnsupportedOperationException();
    }
}
