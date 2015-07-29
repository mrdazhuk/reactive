package com.example.yuriydazhuk.rxexample.api;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Yuriy Dazhuk on 29.07.2015.
 */
public interface ApiSearch {
    @GET("/web")
    SearchResponse doSearch(@Query("v") double apiVersion, @Query("q") String search, @Query("rsz") int pageSize);
}
