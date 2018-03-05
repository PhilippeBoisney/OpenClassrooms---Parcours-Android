package com.openclassrooms.savemytrip.tripbook;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.openclassrooms.savemytrip.R;
import com.openclassrooms.savemytrip.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnCheckedChanged;

public class TripBookActivity extends BaseActivity {

    //FOR DESIGN
    @BindView(R.id.trip_book_activity_external_choice) LinearLayout linearLayoutExternalChoice;
    @BindView(R.id.trip_book_activity_internal_choice) LinearLayout linearLayoutInternalChoice;
    @BindView(R.id.trip_book_activity_radio_external) RadioButton radioButtonExternalChoice;
    @BindView(R.id.trip_book_activity_radio_public) RadioButton radioButtonExternalPublicChoice;
    @BindView(R.id.trip_book_activity_radio_volatile) RadioButton radioButtonInternalVolatileChoice;
    @BindView(R.id.trip_book_activity_edit_text) EditText editText;

    @Override
    public int getLayoutContentViewID() { return R.layout.activity_trip_book; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.configureToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                /*TODO*/
                return true;
            case R.id.action_save:
                /*TODO*/
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // --------------------
    // ACTIONS
    // --------------------

    @OnCheckedChanged({R.id.trip_book_activity_radio_internal, R.id.trip_book_activity_radio_external,
                       R.id.trip_book_activity_radio_private, R.id.trip_book_activity_radio_public,
                       R.id.trip_book_activity_radio_normal, R.id.trip_book_activity_radio_volatile})
    public void onClickRadioButton(CompoundButton button, boolean isChecked){
        /*TODO*/
    }
}