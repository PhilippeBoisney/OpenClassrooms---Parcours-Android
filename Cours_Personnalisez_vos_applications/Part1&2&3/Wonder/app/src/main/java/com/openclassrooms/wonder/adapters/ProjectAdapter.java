package com.openclassrooms.wonder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.openclassrooms.wonder.adapters.ViewHolders.ProjectViewHolder;
import com.openclassrooms.wonder.models.Project;
import com.openclassrooms.wonderfuloc.R;

import java.util.List;

/**
 * Created by Philippe on 27/03/2018.
 */


public class ProjectAdapter extends RecyclerView.Adapter<ProjectViewHolder> {

    // FOR DATA
    private List<Project> projects;
    private RequestManager glide;

    public ProjectAdapter(List<Project> projects, RequestManager glide) {
        this.projects = projects;
        this.glide = glide;
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item, parent, false);

        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder viewHolder, int position) {
        viewHolder.updateWithProject(this.getProject(position), this.glide);
    }

    @Override
    public int getItemCount() {
        return this.projects.size();
    }

    public Project getProject(int position){
        return this.projects.get(position);
    }

    public void update(List<Project> projects){
        this.projects = projects;
        this.notifyDataSetChanged();
    }
}
