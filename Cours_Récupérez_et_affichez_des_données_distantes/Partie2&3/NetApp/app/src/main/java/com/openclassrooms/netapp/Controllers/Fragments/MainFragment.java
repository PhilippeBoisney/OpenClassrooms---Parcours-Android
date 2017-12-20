package com.openclassrooms.netapp.Controllers.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openclassrooms.netapp.Models.GithubUser;
import com.openclassrooms.netapp.R;
import com.openclassrooms.netapp.Utils.GithubCalls;
import com.openclassrooms.netapp.Utils.NetworkAsyncTask;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements NetworkAsyncTask.Listeners, GithubCalls.Callbacks {

    // FOR DESIGN
    @BindView(R.id.fragment_main_textview) TextView textView;

    public MainFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    // -----------------
    // ACTIONS
    // -----------------

    @OnClick(R.id.fragment_main_button)
    public void submit(View view) {
        this.executeHttpRequestWithRetrofit();
    }

    // ------------------------------
    //  HTTP REQUEST (Retrofit Way)
    // ------------------------------

    private void executeHttpRequestWithRetrofit(){
        this.updateUIWhenStartingHTTPRequest();
        GithubCalls.fetchUserFollowing(this, "JakeWharton");
    }

    @Override
    public void onResponse(@Nullable List<GithubUser> users) {
        if (users != null) this.updateUIWithListOfUsers(users);
    }

    @Override
    public void onFailure() {
        this.updateUIWhenStopingHTTPRequest("An error happened !");
    }

    // ------------------
    //  HTTP REQUEST
    // ------------------

    private void executeHttpRequest(){
        new NetworkAsyncTask(this).execute("https://api.github.com/users/JakeWharton/following");
    }

    @Override
    public void onPreExecute() {
        this.updateUIWhenStartingHTTPRequest();
    }

    @Override
    public void doInBackground() { }

    @Override
    public void onPostExecute(String json) {
        this.updateUIWhenStopingHTTPRequest(json);
    }

    // ------------------
    //  UPDATE UI
    // ------------------

    private void updateUIWhenStartingHTTPRequest(){
        this.textView.setText("Downloading...");
    }

    private void updateUIWhenStopingHTTPRequest(String response){
        this.textView.setText(response);
    }

    private void updateUIWithListOfUsers(List<GithubUser> users){
        StringBuilder stringBuilder = new StringBuilder();
        for (GithubUser user : users){
            stringBuilder.append("-"+user.getLogin()+"\n");
        }
        updateUIWhenStopingHTTPRequest(stringBuilder.toString());
    }

}
