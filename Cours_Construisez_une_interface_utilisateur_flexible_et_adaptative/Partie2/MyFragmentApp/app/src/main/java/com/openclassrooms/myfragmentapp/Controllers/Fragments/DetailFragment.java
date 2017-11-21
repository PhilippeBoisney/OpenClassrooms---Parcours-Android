package com.openclassrooms.myfragmentapp.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openclassrooms.myfragmentapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;

public class DetailFragment extends BaseFragment {

    @BindView(R.id.fragment_detail_text_view) TextView textView;
    @State int buttonTag;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected BaseFragment newInstance() { return new DetailFragment(); }

    @Override
    protected int getFragmentLayout() { return R.layout.fragment_detail; }

    @Override
    protected void configureDesign() { }

    @Override
    protected void updateDesign() {
        this.updateTextView(this.buttonTag);
    }

    // --------------
    // UPDATE UI
    // --------------

    //Update TextView depending on TAG's button
    public void updateTextView(int tag){

        //Save tag in ButtonTag variable
        this.buttonTag = tag;

        switch (tag){
            case 10:
                this.textView.setText("You're a very good programmer !");
                break;
            case 20:
                this.textView.setText("I do believe that Jon Snow is going to die in next season...");
                break;
            case 30:
                this.textView.setText("Maybe Game of Thrones next season will get back in 2040 ?");
                break;
            default:
                break;
        }
    }
}
