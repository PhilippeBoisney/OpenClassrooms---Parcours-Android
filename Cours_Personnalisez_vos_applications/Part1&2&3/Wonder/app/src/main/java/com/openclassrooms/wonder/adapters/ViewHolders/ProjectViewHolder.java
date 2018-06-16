package com.openclassrooms.wonder.adapters.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.openclassrooms.wonder.models.Project;
import com.openclassrooms.wonderfuloc.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Philippe on 27/03/2018.
 */

public class ProjectViewHolder extends RecyclerView.ViewHolder {

    // FOR DESIGN
    @BindView(R.id.fragment_main_item_image) ImageView imageView;

    public ProjectViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    // ---

    public void updateWithProject(Project project, RequestManager glide){
        glide.load(project.getCovers().getOriginal()).transition(DrawableTransitionOptions.withCrossFade()).into(imageView);
    }
}
