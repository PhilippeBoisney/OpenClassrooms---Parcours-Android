package com.openclassrooms.magicgithub;

import com.openclassrooms.magicgithub.ui.user_list.ListUserActivity;
import com.openclassrooms.magicgithub.utils.RecyclerViewUtils;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.openclassrooms.magicgithub.utils.RecyclerViewUtils.clickChildView;

/**
 * Instrumented test, which will execute on an Android device.
 * Testing ListUserActivity screen.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserListInstrumentedTest {

    @Rule
    public IntentsTestRule<ListUserActivity> mActivityRule = new IntentsTestRule<>(ListUserActivity.class);

    private int currentUsersSize = -1;

    @Before
    public void setup() {
        currentUsersSize = mActivityRule.getActivity().getUserRepository().getUsers().size();
    }

    @Test
    public void checkIfRecyclerViewIsNotEmpty() {
        onView(withId(R.id.activity_list_user_rv)).check(new RecyclerViewUtils.ItemCount(currentUsersSize));
    }

    @Test
    public void checkIfAddingRandomUserIsWorking() {
        onView(withId(R.id.activity_list_user_fab)).perform(click());
        onView(withId(R.id.activity_list_user_rv)).check(new RecyclerViewUtils.ItemCount(currentUsersSize + 1));
    }

    @Test
    public void checkIfRemovingUserIsWorking() {
        onView(ViewMatchers.withId(R.id.activity_list_user_rv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildView(R.id.item_list_user_delete_button)));
        onView(withId(R.id.activity_list_user_rv)).check(new RecyclerViewUtils.ItemCount(currentUsersSize - 1));
    }
}