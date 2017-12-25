package com.openclassrooms.netapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.openclassrooms.netapp.Models.GithubUser;
import com.openclassrooms.netapp.Models.GithubUserInfo;
import com.openclassrooms.netapp.Utils.GithubStreams;
import com.openclassrooms.netapp.Views.GithubUserAdapter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Philippe on 25/12/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainFragmentTest {

    @Test
    public void fetchUserFollowingTest() throws Exception {
        Observable<List<GithubUser>> observableUsers = GithubStreams.streamFetchUserFollowing("JakeWharton");
        TestObserver<List<GithubUser>> testObserver = new TestObserver<>();

        observableUsers.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        List<GithubUser> usersFetched = testObserver.values().get(0);

        assertThat("Jake Wharton follows only 12 users.",usersFetched.size() == 12);
    }

    @Test
    public void fetchUserInfosTest() throws Exception {
        Observable<GithubUserInfo> observableUser = GithubStreams.streamFetchUserInfos("JakeWharton");
        TestObserver<GithubUserInfo> testObserver = new TestObserver<>();

        observableUser.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        GithubUserInfo userInfo = testObserver.values().get(0);

        assertThat("Jake Wharton Github's ID is 66577.",userInfo.getId() == 66577);
    }
}
