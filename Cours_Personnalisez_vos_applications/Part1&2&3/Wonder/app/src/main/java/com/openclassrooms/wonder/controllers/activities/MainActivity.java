package com.openclassrooms.wonder.controllers.activities;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.BottomNavigationView;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.openclassrooms.wonder.controllers.fragments.MainFragment;
import com.openclassrooms.wonderfuloc.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    //FOR DESIGN
    @BindView(R.id.activity_main_bottom_navigation) BottomNavigationView bottomNavigationView;

    // FOR DATA
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.configureAndShowMainFragment();
        this.configureBottomView();
    }

    // -------------------
    // CONFIGURATION
    // -------------------

    private void configureAndShowMainFragment(){

        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main_frame_layout);

        if (mainFragment == null) {
            mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main_frame_layout, mainFragment)
                    .commit();
        }
    }

    private void configureBottomView(){
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> updateMainFragment(item.getItemId()));
    }

    // -------------------
    // UI
    // -------------------

    private Boolean updateMainFragment(Integer integer){
        switch (integer) {
            case R.id.action_android:
                this.mainFragment.updateDesignWhenUserClickedBottomView(MainFragment.REQUEST_ANDROID);
                break;
            case R.id.action_logo:
                this.mainFragment.updateDesignWhenUserClickedBottomView(MainFragment.REQUEST_LOGO);
                break;
            case R.id.action_landscape:
                this.mainFragment.updateDesignWhenUserClickedBottomView(MainFragment.REQUEST_LANDSCAPE);
                break;
        }
        return true;
    }

    // -------------------
    // TEST
    // -------------------

    @VisibleForTesting
    public CountingIdlingResource getEspressoIdlingResourceForMainFragment() {
        return this.mainFragment.getEspressoIdlingResource();
    }
}
