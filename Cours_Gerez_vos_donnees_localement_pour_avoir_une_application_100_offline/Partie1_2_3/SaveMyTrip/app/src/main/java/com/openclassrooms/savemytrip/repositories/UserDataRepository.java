package com.openclassrooms.savemytrip.repositories;

import android.arch.lifecycle.LiveData;

import com.openclassrooms.savemytrip.database.dao.UserDao;
import com.openclassrooms.savemytrip.models.User;

/**
 * Created by Philippe on 28/02/2018.
 */

public class UserDataRepository {

    private final UserDao userDao;

    public UserDataRepository(UserDao userDao) { this.userDao = userDao; }

    // --- GET USER ---
    public LiveData<User> getUser(long userId) { return this.userDao.getUser(userId); }
}
