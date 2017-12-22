package com.openclassrooms.netapp.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.netapp.Models.GithubUser;
import com.openclassrooms.netapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Philippe on 22/12/2017.
 */

public class GithubUserViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_main_item_title) TextView textView;
    @BindView(R.id.fragment_main_item_website) TextView texViewWebsite;
    @BindView(R.id.fragment_main_item_image) ImageView imageView;

    public GithubUserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithGithubUser(GithubUser githubUser, RequestManager glide){
        this.textView.setText(githubUser.getLogin());
        this.texViewWebsite.setText(githubUser.getHtmlUrl());
        glide.load(githubUser.getAvatarUrl()).apply(RequestOptions.circleCropTransform()).into(imageView);
    }
}
