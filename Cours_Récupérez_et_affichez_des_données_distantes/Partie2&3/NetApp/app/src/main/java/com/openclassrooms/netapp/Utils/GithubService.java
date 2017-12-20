package com.openclassrooms.netapp.Utils;

import com.openclassrooms.netapp.Models.GithubUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Philippe on 20/12/2017.
 */

public interface GithubService {
    @GET("users/{username}/following")
    Call<List<GithubUser>> getFollowing(@Path("username") String username);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
