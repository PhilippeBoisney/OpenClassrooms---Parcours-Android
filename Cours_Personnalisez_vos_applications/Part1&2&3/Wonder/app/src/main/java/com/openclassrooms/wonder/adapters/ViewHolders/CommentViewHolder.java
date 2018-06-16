package com.openclassrooms.wonder.adapters.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.wonder.models.Comment;
import com.openclassrooms.wonderfuloc.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Philippe on 27/03/2018.
 */

public class CommentViewHolder extends RecyclerView.ViewHolder {

    // FOR DESIGN
    @BindView(R.id.modal_fragment_comments_item_image) ImageView imageView;
    @BindView(R.id.modal_fragment_comments_item_text_view) TextView textView;
    @BindView(R.id.modal_fragment_comments_item_text_view_date) TextView textViewDate;

    public CommentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    // ---

    public void updateWithComment(Comment comment, RequestManager glide){
        glide.load(comment.getUser().getImages().getImageProfile()).apply(RequestOptions.circleCropTransform()).into(imageView);
        textView.setText(comment.getComment());
        textViewDate.setText(getDate(comment.getCreatedOn()));
    }

    // ---

    private String getDate(Integer dateCreatedOn){
        try {
            long timestamp = dateCreatedOn * 1000L;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date netDate = (new Date(timestamp));
            return sdf.format(netDate);
        }
        catch(Exception ex){
            return "...";
        }
    }
}
