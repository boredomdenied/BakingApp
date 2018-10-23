package com.boredomdenied.bakingapp;

import android.content.ComponentName;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.boredomdenied.bakingapp.ui.RecipeActivity;
import com.boredomdenied.bakingapp.ui.RecipeListActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RecipeActivityTest2 {

    @Rule
    public ActivityTestRule<RecipeActivity> mActivityRule = new ActivityTestRule(
            RecipeActivity.class);

    @Test
    public void checkRecyclerView() {
        onView(withId(R.id.customRecyclerView)).check(matches(isDisplayed()));
    }

    @Before
    public void startIntent() {
        Intents.init();
    }

    @Test
    public void checkStartsListActivity() {
        onView(withId(R.id.customRecyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition(1,click()));
        intended(hasComponent(new ComponentName(getTargetContext(), RecipeListActivity.class)));
        Intents.release();

    }

}

