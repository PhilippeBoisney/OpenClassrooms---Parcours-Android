package com.openclassrooms.netapp.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.openclassrooms.netapp.Models.GithubUser;
import com.openclassrooms.netapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Philippe on 22/12/2017.
 */

public class GithubUserViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_main_item_title) TextView textView;

    public GithubUserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithGithubUser(GithubUser githubUser){
        this.textView.setText(githubUser.getLogin());
    }
}
