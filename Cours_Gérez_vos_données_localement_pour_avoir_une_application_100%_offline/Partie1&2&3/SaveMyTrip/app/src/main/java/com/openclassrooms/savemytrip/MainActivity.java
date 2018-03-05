package com.openclassrooms.savemytrip;

import android.content.Intent;

import com.openclassrooms.savemytrip.base.BaseActivity;
import com.openclassrooms.savemytrip.todolist.TodoListActivity;
import com.openclassrooms.savemytrip.tripbook.TripBookActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutContentViewID() { return R.layout.activity_main; }

    // --------------------
    // ACTIONS
    // --------------------

    @OnClick(R.id.main_activity_button_trip_book)
    public void onClickTripBookButton() {
        Intent intent = new Intent(this, TripBookActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.main_activity_button_todo_list)
    public void onClickTodoListButton() {
        Intent intent = new Intent(this, TodoListActivity.class);
        startActivity(intent);
    }
}
