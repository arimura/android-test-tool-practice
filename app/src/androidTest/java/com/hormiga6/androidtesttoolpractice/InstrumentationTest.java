package com.hormiga6.androidtesttoolpractice;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class InstrumentationTest {

    @Test
    public void testInstrumentation(){
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();

        //this context is for com.hormiga6.androidtesttoolpractice.test, not com.hormiga6.androidtesttoolpractice
        assertThat(instrumentation.getContext().getPackageName(),is("com.hormiga6.androidtesttoolpractice.test"));
        assertThat(instrumentation.getTargetContext().getPackageName(),is("com.hormiga6.androidtesttoolpractice"));
    }
}
