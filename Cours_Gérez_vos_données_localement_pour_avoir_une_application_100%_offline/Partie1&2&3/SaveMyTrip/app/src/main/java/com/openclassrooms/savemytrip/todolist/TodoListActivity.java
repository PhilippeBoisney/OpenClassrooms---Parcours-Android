package com.openclassrooms.savemytrip.todolist;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.openclassrooms.savemytrip.R;
import com.openclassrooms.savemytrip.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class TodoListActivity extends BaseActivity {

    // FOR DESIGN
    @BindView(R.id.todo_list_activity_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.todo_list_activity_spinner) Spinner spinner;
    @BindView(R.id.todo_list_activity_edit_text) EditText editText;
    @BindView(R.id.todo_list_activity_header_profile_image) ImageView profileImage;
    @BindView(R.id.todo_list_activity_header_profile_text) TextView profileText;

    @Override
    public int getLayoutContentViewID() { return R.layout.activity_todo_list; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.configureToolbar();
        this.configureSpinner();
    }

    // -------------------
    // ACTIONS
    // -------------------

    @OnClick(R.id.todo_list_activity_button_add)
    public void onClickAddButton() { /*TODO*/ }

    // -------------------
    // UI
    // -------------------

    private void configureSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
