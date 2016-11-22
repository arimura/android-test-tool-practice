package com.hormiga6.androidtesttoolpractice;

import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by kotaro.arimura on 2016/11/22.
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class ReadDataTest {
    private static final String TAG = "ReadDataTest";

    @Rule
    public ActivityTestRule<ListActivity> activityActivityTestRule = new ActivityTestRule<ListActivity>(ListActivity.class);

    @Test
    public void testRead(){
        assertThat(new File("").getAbsoluteFile().toString(), is("/"));
        assertThat(Util.getCurrentActivity().getFilesDir().toString(), is("/data/user/0/com.hormiga6.androidtesttoolpractice/files"));
    }
}
