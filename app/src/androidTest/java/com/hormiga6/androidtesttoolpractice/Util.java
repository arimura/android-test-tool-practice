package com.hormiga6.androidtesttoolpractice;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.collect.Iterables;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;

import java.util.Collection;

/**
 * Created by kotaro.arimura on 2016/11/22.
 */

public class Util {
    public static Activity getCurrentActivity() {
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        final Activity[] activities = new Activity[1];
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                Collection<Activity> activitiesInStage = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                activities[0] = Iterables.getOnlyElement(activitiesInStage);
            }
        });
        return activities[0];
    }
}
