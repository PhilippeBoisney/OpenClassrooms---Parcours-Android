package com.openclassrooms.magicgithub.ui.user_list;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.magicgithub.R;
import com.openclassrooms.magicgithub.model.User;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListUserViewHolder extends RecyclerView.ViewHolder {

    // FOR DESIGN ---
    private ImageView avatar;
    private TextView username;
    private ImageButton deleteButton;

    public ListUserViewHolder(@NonNull View itemView) {
        super(itemView);
        avatar = itemView.findViewById(R.id.item_list_user_avatar);
        username = itemView.findViewById(R.id.item_list_user_username);
        deleteButton = itemView.findViewById(R.id.item_list_user_delete_button);
    }

    public void bind(User user, UserListAdapter.Listener callback) {
        Glide.with(itemView.getContext())
                .load(user.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(avatar);
        username.setText(user.getLogin());
        deleteButton.setOnClickListener(view -> callback.onClickDelete(user));
    }
}
