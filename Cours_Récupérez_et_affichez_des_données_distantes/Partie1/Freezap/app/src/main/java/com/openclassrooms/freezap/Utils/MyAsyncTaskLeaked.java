package com.openclassrooms.freezap.Utils;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by Philippe on 19/12/2017.
 */

public class MyAsyncTaskLeaked extends AsyncTask<Void, Void, Long> {

    private final ProgressBar progressBar;

    // Constructor
    public MyAsyncTaskLeaked(ProgressBar progressBar){
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.progressBar.setVisibility(View.VISIBLE);
        Log.e("TAG", "AsyncTask is started.");
    }

    @Override
    protected void onPostExecute(Long success) {
        super.onPostExecute(success);
        this.progressBar.setVisibility(View.GONE);
        Log.e("TAG", "AsyncTask is finished.");
    }

    @Override
    protected Long doInBackground(Void... voids) {
        Log.e("TAG", "AsyncTask doing some big work...");
        return Utils.executeLongActionDuring7seconds(); // Execute our task
    }
}
