package com.openclassrooms.wonder.api;

import com.openclassrooms.wonder.models.ApiResponse;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Philippe on 27/03/2018.
 */

public interface BehanceService {

    final static String BASE_URL = "https://api.behance.net/v2/";
    final static String TOKEN = "cYSJrc7NYQxc0FlXhbIkuC2qviZSfY4i";

    @GET("projects?client_id="+TOKEN)
    Observable<ApiResponse> getProjects(@Query("q") String request);

    @GET("projects/{project_id}?client_id="+TOKEN)
    Observable<ApiResponse> getProject(@Path("project_id") Integer projectId);

    @GET("projects/{project_id}/comments?client_id="+TOKEN)
    Observable<ApiResponse> getComments(@Path("project_id") Integer projectId);

    // ---

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
