package com.openclassrooms.wonder.controllers.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.openclassrooms.wonderfuloc.R;
import com.openclassrooms.wonder.controllers.fragments.DetailFragment;

import butterknife.BindView;

public class DetailActivity extends AppCompatActivity {

    // FOR DATA
    private  DetailFragment detailFragment;
    public static final String BUNDLE_KEY_PROJECT_ID = "BUNDLE_KEY_PROJECT_ID";
    public static final String BUNDLE_KEY_PROJECT_IMAGE_URL = "BUNDLE_KEY_PROJECT_IMAGE_URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        this.configureToolbar();
        this.configureAndShowDetailFragment();
    }

    // -------------------
    // CONFIGURATION
    // -------------------

    private void configureAndShowDetailFragment(){

        detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.activity_detail_frame_layout);

        if (detailFragment == null) {
            detailFragment = new DetailFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_detail_frame_layout, detailFragment)
                    .commit();
        }
    }

    private void configureToolbar(){
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
