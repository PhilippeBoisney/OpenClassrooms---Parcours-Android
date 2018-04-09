package com.openclassrooms.wonder;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.wonder.controllers.activities.MainActivity;
import com.openclassrooms.wonderfuloc.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static com.openclassrooms.wonder.matchers.BottomNavigationItemViewMatcher.withIsChecked;
import static com.openclassrooms.wonder.matchers.BottomNavigationItemViewMatcher.withTitle;


@RunWith(AndroidJUnit4.class)
public class MainActivityTests {

    // FOR DATA
    private Context context;
    private IdlingResource mIdlingResource;

    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        this.context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void checkBottomNavigationButtonSelection(){
        onView(ViewMatchers.withId(R.id.action_android)).check(matches(withIsChecked(true)));
        onView(ViewMatchers.withId(R.id.action_landscape)).check(matches(withIsChecked(false)));
        onView(ViewMatchers.withId(R.id.action_logo)).check(matches(withIsChecked(false)));
    }

    @Test
    public void checkBottomNavigationButtonTitle(){
        onView(ViewMatchers.withId(R.id.action_android)).check(matches(withTitle(context.getString(R.string.bottom_navigation_menu_android))));
        onView(ViewMatchers.withId(R.id.action_landscape)).check(matches(withTitle(context.getString(R.string.bottom_navigation_menu_landscape))));
        onView(ViewMatchers.withId(R.id.action_logo)).check(matches(withTitle(context.getString(R.string.bottom_navigation_menu_logos))));
    }

    @Test
    public void checkIfRecyclerViewIsNotEmpty() throws Exception {
        this.waitForNetworkCall();
        onView(ViewMatchers.withId(R.id.fragment_main_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    // ---

    private void waitForNetworkCall(){
        this.mIdlingResource = mActivityRule.getActivity().getEspressoIdlingResourceForMainFragment();
        IdlingRegistry.getInstance().register(mIdlingResource);
    }
}
