package com.openclassrooms.magicgithub.utils;

import com.openclassrooms.magicgithub.model.User;

import java.util.List;
import androidx.recyclerview.widget.DiffUtil;

public class UserDiffCallback extends DiffUtil.Callback{

    private final List<User> oldUsers;
    private final List<User> newUsers;

    public UserDiffCallback(List<User> newUsers, List<User> oldUsers) {
        this.newUsers = newUsers;
        this.oldUsers = oldUsers;
    }

    @Override
    public int getOldListSize() {
        return oldUsers.size();
    }

    @Override
    public int getNewListSize() {
        return newUsers.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldUsers.get(oldItemPosition).getId() == newUsers.get(newItemPosition).getId() ;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldUsers.get(oldItemPosition).equals(newUsers.get(newItemPosition));
    }
}