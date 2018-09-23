package com.nils27.publicflikrfeed;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.espresso.contrib.RecyclerViewActions;

import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class TestDetailsActivityShown {

    @Rule
    public ActivityTestRule<MainActivity> mDetailsMetaDataRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void MetaData_Exists() {
        //Needs to click through the recyclerView to access the details activity
        //need idling resource as delay in getting data from network
        onView(ViewMatchers.withId(R.id.rv_main)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        //need another idling resource as delay in setting data in widgets
        //check details view is displayed
        onView(ViewMatchers.withId(R.id.tv_Title)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.tv_Author)).check(matches(isDisplayed()));
        //Data always has an Author therefore can check to see if it has data within it
        onView(ViewMatchers.withId(R.id.tv_Author)).check(matches(not(withText(""))));

        //check for ImageView ContentDescription
        onView(ViewMatchers.withId(R.id.iv_details)).check(matches(hasContentDescription()));
    }
}