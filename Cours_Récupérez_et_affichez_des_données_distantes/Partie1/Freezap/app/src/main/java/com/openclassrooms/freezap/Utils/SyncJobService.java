package com.openclassrooms.freezap.Utils;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

/**
 * Created by Philippe on 18/12/2017.
 */

public class SyncJobService extends JobService implements MyAsyncTask.Listeners {

    private MyAsyncTask jobTask;
    private JobParameters jobParameters;

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        Log.e(this.getClass().getSimpleName(), "SyncJob is started.");
        this.jobParameters = jobParameters;
        this.jobTask = new MyAsyncTask(this);
        this.jobTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(final JobParameters params) {
        Log.e(this.getClass().getSimpleName(), "SyncJob is stopped !");
        if (this.jobTask != null) this.jobTask.cancel(true);
        return false;
    }

    @Override
    public void onPreExecute() {  }

    @Override
    public void doInBackground() { }

    @Override
    public void onPostExecute(Long taskEnd) {
        Log.e("TAG", "Task ended at : "+taskEnd);
        jobFinished(jobParameters, false);
    }
}
