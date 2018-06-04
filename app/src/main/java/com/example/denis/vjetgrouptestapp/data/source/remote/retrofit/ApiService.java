package com.example.denis.vjetgrouptestapp.data.source.remote.retrofit;

import com.example.denis.vjetgrouptestapp.data.model.BaseResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/v2/everything")
    Single<BaseResponse> getArticles(@Query("apiKey") String apiKey,
                                     @Query("sortBy") String sortBy,
                                     @Query("sources") String sources,
                                     @Query("from") String fromDate,
                                     @Query("to") String toDate,
                                     @Query("page") int page,
                                     @Query("pageSize") int pageSize

    );

    @GET("/v2/top-headlines")
    Single<BaseResponse> getTopHeadlines(@Query("apiKey") String apiKey,
                                       @Query("language") String language
    );

    @GET("/v2/sources")
    Single<BaseResponse> getSources(@Query("apiKey") String apiKey,
                                    @Query("language") String language
    );
}
