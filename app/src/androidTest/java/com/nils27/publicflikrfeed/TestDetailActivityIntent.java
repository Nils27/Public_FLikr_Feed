package com.nils27.publicflikrfeed;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;

public class TestDetailActivityIntent {

    @Rule
    public ActivityTestRule<MainActivity> mDetailsIntentDataRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void CheckIntentToDetailActvitiy() {
        Intents.init();
        try {
            onView(ViewMatchers.withId(R.id.rv_main)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
            intended(hasComponent(DetailsActivity.class.getName()));
        } finally {
            Intents.release();
        }
    }

    @Test
    public void CheckIntentExtrasArePresent() {
        Intents.init();
        try {
            onView(ViewMatchers.withId(R.id.rv_main)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
            intended(hasExtraWithKey(MainActivity.ITEM_PASSED_KEY));
            intended(hasExtraWithKey(MainActivity.MEDIA_PASSED_KEY));
        } finally {
            Intents.release();
        }
    }

}
