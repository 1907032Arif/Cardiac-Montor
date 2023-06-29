package com.example.cardiac_monitor;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.Espresso.onView;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.MatcherAssert.assertThat;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest


public class SplashAcitivityTest {

    @Test
    public void test_isActivityInView() {
        ActivityScenario<SplashActivity> activityScenario = ActivityScenario.launch(SplashActivity.class);
        onView(withId(R.id.splash)).check(matches(isDisplayed()));
    }

    @Test
    public void test_isText_Progress_inView() {
        ActivityScenario<SplashActivity> activityScenario = ActivityScenario.launch(SplashActivity.class);
        //image ta display hoise kina
        onView(withId(R.id.GfG_full_logo)).check(matches(isDisplayed()));
        //developed by text ta display korlo kina
        onView(withId(R.id.editTextTextMultiLine)).check(matches(isDisplayed()));
        //progress bar ta display korlo kina
        onView(withId(R.id.pbProcessing)).check(matches(isDisplayed()));
        //app title ta display krlo kina
        onView(withId(R.id.textView)).check(matches((isDisplayed())));

    }
}
