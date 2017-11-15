package com.openclassrooms.myfragmentapp.Controllers.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.openclassrooms.myfragmentapp.Controllers.Fragments.MainFragment;
import com.openclassrooms.myfragmentapp.R;

public class MainActivity extends AppCompatActivity implements MainFragment.OnButtonClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // --------------
    // CallBack
    // --------------

    @Override
    public void onButtonClicked(View view) {
        Log.e(getClass().getSimpleName(),"Button clicked !");
        startActivity(new Intent(this, DetailActivity.class));
    }
}
