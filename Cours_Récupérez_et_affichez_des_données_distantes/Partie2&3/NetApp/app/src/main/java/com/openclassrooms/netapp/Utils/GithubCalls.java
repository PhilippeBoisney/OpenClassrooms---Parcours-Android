package com.openclassrooms.netapp.Utils;

import android.support.annotation.Nullable;

import com.openclassrooms.netapp.Models.GithubUser;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Philippe on 20/12/2017.
 */

public class GithubCalls {

    // Creating a callback
    public interface Callbacks {
        void onResponse(@Nullable List<GithubUser> users);
        void onFailure();
    }

    // Public method to start fetching users following by Jake Wharton
    public static void fetchUserFollowing(Callbacks callbacks, String username){

        // Create a weak reference to callback (avoid memory leaks)
        final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<Callbacks>(callbacks);

        // Get a Retrofit instance and the related endpoints
        GithubService gitHubService = GithubService.retrofit.create(GithubService.class);

        // Create the call on Github API
        Call<List<GithubUser>> call = gitHubService.getFollowing(username);
        // Start the call
        call.enqueue(new Callback<List<GithubUser>>() {

            @Override
            public void onResponse(Call<List<GithubUser>> call, Response<List<GithubUser>> response) {
                // Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<GithubUser>> call, Throwable t) {
                // Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
            }
        });
    }
}

