package com.nils27.publicflikrfeed;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;

public class TestRecyclerviewClick {
    @Rule
    public ActivityTestRule<MainActivity> mRecyclerViewClickRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void RecyclerView_Click() {
        //need idling resource as delay in getting data from network
        //also proves there data has come back from network
        onView(ViewMatchers.withId(R.id.rv_main)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }
}
