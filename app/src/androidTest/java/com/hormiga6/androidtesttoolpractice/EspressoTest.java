package com.hormiga6.androidtesttoolpractice;

import android.app.Activity;
import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.squareup.spoon.Spoon;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.hormiga6.androidtesttoolpractice.Util.getCurrentActivity;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;

/**
 * Created by kotaro.arimura on 2016/11/21.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest {
    @Rule
    public ActivityTestRule<ListActivity> activityActivityTestRule = new ActivityTestRule<ListActivity>(ListActivity.class, false, false);

    @Before
    public void setup(){
        Intent intent = new Intent();
        intent.putExtra("hoge","baaa");
        activityActivityTestRule.launchActivity(intent);
    }

    @Test
    public void testShow() throws InterruptedException {
        onView(withId(R.id.editTextHeader)).check(matches(withText("baaa")));
        onView(withId(R.id.editTextHeader)).perform(clearText(), typeText("hoge"), closeSoftKeyboard());
        onView(withId(R.id.buttonHeader)).perform(click());
        onView(withId(R.id.editTextHeader)).check(matches(withText("hoge")));
        Spoon.screenshot(getCurrentActivity(), "testShow");
    }

    @Test
    public void testOnData() throws InterruptedException {
        onView(withText("1")).check(matches(withText("1")));
        onData(allOf(is("1"))).perform(click());
    }

    @Test
    public void testActivityName() {
        Activity currentActivity = getCurrentActivity();
        assertThat((ListActivity) currentActivity, isA(ListActivity.class));
    }
}
