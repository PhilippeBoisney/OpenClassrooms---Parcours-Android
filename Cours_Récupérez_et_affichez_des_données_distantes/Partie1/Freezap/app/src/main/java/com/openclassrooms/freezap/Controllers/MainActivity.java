package com.openclassrooms.freezap.Controllers;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.openclassrooms.freezap.R;
import com.openclassrooms.freezap.Utils.MyAsyncTask;
import com.openclassrooms.freezap.Utils.MyHandlerThread;
import com.openclassrooms.freezap.Utils.Utils;

public class MainActivity extends AppCompatActivity implements MyAsyncTask.Listeners {

    //FOR DESIGN
    private ProgressBar progressBar;

    //FOR DATA
    private MyHandlerThread handlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get progressbar from layout
        this.progressBar = findViewById(R.id.activity_main_progress_bar);

        //Configure Handler Thread
        this.configureHandlerThread();
    }

    @Override
    protected void onDestroy() {
        handlerThread.quit(); // QUIT HANDLER THREAD (Free precious resources)
        super.onDestroy();
    }

    // ------------
    // ACTIONS
    // ------------

    public void onClickButton(View v){
        int buttonTag = Integer.valueOf(v.getTag().toString());
        switch (buttonTag){
            case 10: // CASE USER CLICKED ON BUTTON "EXECUTE ACTION IN MAIN THREAD"
                Utils.executeLongActionDuring7seconds();
                break;
            case 20: // CASE USER CLICKED ON BUTTON "EXECUTE ACTION IN BACKGROUND"
                this.startHandlerThread();
                break;
            case 30:
                break;
            case 40:
                break;
            case 50:
                break;
            case 60: // CASE USER CLICKED ON BUTTON "EXECUTE ASYNCTASK"
                this.startAsyncTask();
                break;
            case 70:
                break;
        }
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    // Configuring the HandlerThread
    private void configureHandlerThread(){
        handlerThread = new MyHandlerThread("MyAwesomeHanderThread", this.progressBar);
    }

    // -------------------------------------------
    // BACKGROUND TASK (HandlerThread & AsyncTask)
    // -------------------------------------------

    // EXECUTE HANDLER THREAD
    private void startHandlerThread(){
        handlerThread.startHandler();
    }

    // ------

    // EXECUTE ASYNC TASK
    private void startAsyncTask() {
        new MyAsyncTask(this).execute();
    }

    @Override
    public void onPreExecute() {
        this.updateUIBeforeTask();
    }

    @Override
    public void doInBackground() { }

    @Override
    public void onPostExecute(Long taskEnd) {
        this.updateUIAfterTask(taskEnd);
    }

    // -----------------
    // UPDATE UI
    // -----------------

    public void updateUIBeforeTask(){
        progressBar.setVisibility(View.VISIBLE);
    }

    public void updateUIAfterTask(Long taskEnd){
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Task is finally finished at : "+taskEnd+" !", Toast.LENGTH_SHORT).show();
    }
}
