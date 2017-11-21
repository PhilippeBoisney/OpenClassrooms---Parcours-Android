package com.openclassrooms.myfragmentapp.Controllers.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.myfragmentapp.R;

import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * Created by Philippe on 21/11/2017.
 */

public abstract class BaseFragment extends Fragment {

    // Force developer implement those methods
    protected abstract BaseFragment newInstance();
    protected abstract int getFragmentLayout();
    protected abstract void configureDesign();
    protected abstract void updateDesign();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Get layout identifier from abstract method
        View view = inflater.inflate(getFragmentLayout(), container, false);
        // Binding Views
        ButterKnife.bind(this, view);
        // Configure Design (Developer will call this method instead of override onCreateView())
        this.configureDesign();
        return(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Handling Bundle Restoration
        Icepick.restoreInstanceState(this, savedInstanceState);
        // Update Design (Developer will call this method instead of override onActivityCreated())
        this.updateDesign();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Handling Bundle Save
        Icepick.saveInstanceState(this, outState);
    }
}
