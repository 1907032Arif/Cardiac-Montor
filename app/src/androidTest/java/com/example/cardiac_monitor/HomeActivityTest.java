package com.example.cardiac_monitor;

import static android.app.PendingIntent.getActivity;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;

// ...



public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> activityRule =
            new ActivityTestRule<>(HomeActivity.class);



    @Test
    public void clickAddButton_opensPopupDialog() {
        // Click the "Add" button
        onView(withId(R.id.add))
                .perform(click());

        // Check if the pop-up dialog is displayed
        onView(withId(R.id.popUpDialog)) // Replace with the actual title of the pop-up dialog
                .inRoot(isDialog()) // Specify that the view is inside a dialog
                .check(matches(isDisplayed()));
       // onView(withId(R.id.splash)).check(matches(isDisplayed()));
    }

    @Test
    public void dialogElementsAreDisplayed() {
        // Click the "Add" button
        onView(withId(R.id.add))
                .perform(click());

        // Check if the dialog elements are displayed
        onView(withId(R.id.systolic))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
        onView(withId(R.id.diastolic))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
        onView(withId(R.id.date))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
        onView(withId(R.id.time))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
        onView(withId(R.id.comments))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
        onView(withId(R.id.pulse_rate))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
        onView(withId(R.id.yes_btn))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
        onView(withId(R.id.no_btn))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
    }

    @Test
    public void enterDataInPopupDialog() {
        // Click the "Add" button
        onView(withId(R.id.add))
                .perform(click());

        // Enter data into the textboxes
        onView(withId(R.id.systolic))
                .inRoot(isDialog())
                .perform(replaceText("120")); // Replace with your desired value
        onView(withId(R.id.diastolic))
                .inRoot(isDialog())
                .perform(replaceText("80")); // Replace with your desired value
        onView(withId(R.id.date))
                .inRoot(isDialog())
                .perform(replaceText("2023-06-29")); // Replace with your desired value
        onView(withId(R.id.time))
                .inRoot(isDialog())
                .perform(replaceText("09:30 AM")); // Replace with your desired value
        onView(withId(R.id.comments))
                .inRoot(isDialog())
                .perform(replaceText("Test comment")); // Replace with your desired value
        onView(withId(R.id.pulse_rate))
                .inRoot(isDialog())
                .perform(replaceText("70")); // Replace with your desired value

        // Click the "Yes" button to add the entry
        onView(withId(R.id.yes_btn))
                .inRoot(isDialog())
                .perform(click());

    }

    @Test
    public void testRecordsButton() {
        ActivityScenario<HomeActivity> activityScenarioRule = ActivityScenario.launch(HomeActivity.class);


        // Perform click action on the "Records" button
        onView(withId(R.id.records))
                .perform(click());

        // Verify that the RecordsActivity is launched
        onView(withId(R.id.records_activity)).check(matches(isDisplayed()));


    }



}



