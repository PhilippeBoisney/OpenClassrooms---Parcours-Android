package com.openclassrooms.wonder.matchers;

import android.support.design.internal.BottomNavigationItemView;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Created by Philippe on 29/03/2018.
 */

public final class BottomNavigationItemViewMatcher {

    private BottomNavigationItemViewMatcher(){}

    public static Matcher<View> withIsChecked(final boolean isChecked) {
        return new BoundedMatcher<View, BottomNavigationItemView>(BottomNavigationItemView.class) {

            private boolean triedMatching;

            @Override
            public void describeTo(Description description) {
                if (triedMatching) {
                    description.appendText("with isChecked: " + String.valueOf(isChecked));
                    description.appendText("But was: " + String.valueOf(!isChecked));
                }
            }

            @Override
            protected boolean matchesSafely(BottomNavigationItemView item) {
                triedMatching = true;
                return item.getItemData().isChecked() == isChecked;
            }
        };
    }

    public static Matcher<View> withTitle(final String titleTested) {
        return new BoundedMatcher<View, BottomNavigationItemView>(BottomNavigationItemView.class) {

            private boolean triedMatching;
            private String title;

            @Override
            public void describeTo(Description description) {
                if (triedMatching) {
                    description.appendText("with title: " + titleTested);
                    description.appendText("But was: " + String.valueOf(title));
                }
            }

            @Override
            protected boolean matchesSafely(BottomNavigationItemView item) {
                this.triedMatching = true;
                this.title = item.getItemData().getTitle().toString();
                return title.equals(titleTested);
            }
        };
    }
}

