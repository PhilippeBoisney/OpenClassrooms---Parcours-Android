package com.openclassrooms.myfragmentapp.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openclassrooms.myfragmentapp.R;

public class DetailFragment extends Fragment {

    // Declare TextView
    private TextView textView;
    //Declare a buttonTag tracking
    private int buttonTag;
    //Create static variable to identify key in Bundle
    private static final String KEY_BUTTONTAG = "com.openclassrooms.myfragmentapp.Controllers.Fragments.DetailFragment.KEY_BUTTONTAG";


    public DetailFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        //Get textView from layout (don't forget to create ID in fragment_detail.xml)
        this.textView = (TextView) view.findViewById(R.id.fragment_detail_text_view);
        return(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Restore last buttonTag if possible
        if (savedInstanceState != null) {
            int buttonTagRestored = savedInstanceState.getInt(KEY_BUTTONTAG, 0);
            //Update TextView
            this.updateTextView(buttonTagRestored);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save buttonTag in Bundle when fragment is destroyed
        outState.putInt(KEY_BUTTONTAG, buttonTag);
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
