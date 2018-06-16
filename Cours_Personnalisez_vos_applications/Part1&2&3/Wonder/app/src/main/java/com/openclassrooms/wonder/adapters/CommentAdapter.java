package com.openclassrooms.wonder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.openclassrooms.wonder.adapters.ViewHolders.CommentViewHolder;
import com.openclassrooms.wonder.models.Comment;
import com.openclassrooms.wonderfuloc.R;

import java.util.List;

/**
 * Created by Philippe on 27/03/2018.
 */


public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    // FOR DATA
    private List<Comment> comments;
    private RequestManager glide;

    public CommentAdapter(List<Comment> comments, RequestManager glide) {
        this.comments = comments;
        this.glide = glide;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.modal_fragment_comments_item, parent, false);

        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder viewHolder, int position) {
        viewHolder.updateWithComment(this.getComment(position), this.glide);
    }

    @Override
    public int getItemCount() { return this.comments.size(); }

    public Comment getComment(int position){ return this.comments.get(position); }

    public void update(List<Comment> comments){
        this.comments = comments;
        this.notifyDataSetChanged();
    }
}
