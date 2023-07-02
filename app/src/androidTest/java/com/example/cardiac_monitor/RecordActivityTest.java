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

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


import androidx.test.rule.ActivityTestRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * This class contains Espresso UI tests for the RecordsActivity.
 */

public class RecordActivityTest {
    /**
     * Rule to launch the RecordsActivity for testing.
     */
    @Rule
    public ActivityScenarioRule<RecordsActivity> activityScenarioRule =
            new ActivityScenarioRule<>(RecordsActivity.class);



    /**
     * Test case to verify the update functionality in the RecordsActivity.
     * It performs the following steps:
     * 1. Clicks on an item in the list view.
     * 2. Checks if the pop-up dialog is displayed.
     * 3. Clicks the "Update" button to open the pop-up dialog.
     * 4. Verifies if the pop-up dialog is displayed.
     * 5. Verifies if the required dialog elements are displayed.
     * 6. Enters data into the textboxes.
     * 7. Clicks the "Yes" button to add the entry.
     */
    @Test
    public void testUpdate() {
        ActivityScenario<RecordsActivity> activityScenario = activityScenarioRule.getScenario();

        activityScenario.onActivity(activity -> {
            // Simulate clicking on an item in the list view
            int itemPosition = 2; // Replace with the desired item position
            activity.listView.performItemClick(
                    activity.listView.getAdapter().getView(itemPosition, null, null),
                    itemPosition,
                    activity.listView.getAdapter().getItemId(itemPosition)
            );


        });

        // Check if the pop-up dialog is displayed
        onView(withId(R.id.action_popUp)) // Replace with the actual title of the pop-up dialog
                .inRoot(isDialog()) // Specify that the view is inside a dialog
                .check(matches(isDisplayed()));

        // Click the "Update" button
        onView(withId(R.id.update))
                .perform(click());

        // Check if the pop-up dialog is displayed
        onView(withId(R.id.popUpDialog)) // Replace with the actual title of the pop-up dialog
                .inRoot(isDialog()) // Specify that the view is inside a dialog
                .check(matches(isDisplayed()));

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



    /**
     * Test case to verify the delete functionality in the RecordsActivity.
     * It performs the following steps:
     * 1. Clicks on an item in the list view.
     * 2. Checks if the pop-up dialog is displayed.
     * 3. Clicks the "Delete" button to delete the entry.
     */
    @Test
    public void testDelete() {
        ActivityScenario<RecordsActivity> activityScenario = activityScenarioRule.getScenario();

        activityScenario.onActivity(activity -> {
            // Simulate clicking on an item in the list view
            int itemPosition = 2; // Replace with the desired item position
            activity.listView.performItemClick(
                    activity.listView.getAdapter().getView(itemPosition, null, null),
                    itemPosition,
                    activity.listView.getAdapter().getItemId(itemPosition)
            );


        });
        // Check if the pop-up dialog is displayed
        onView(withId(R.id.action_popUp)) // Replace with the actual title of the pop-up dialog
                .inRoot(isDialog()) // Specify that the view is inside a dialog
                .check(matches(isDisplayed()));



        // Click the "Update" button
        onView(withId(R.id.delete))
                .perform(click());

}
}

