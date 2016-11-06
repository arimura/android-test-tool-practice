package com.hormiga6.androidtesttoolpractice;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

/**
 * Created by kotaro.arimura on 2016/10/25.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class MockAndroidTest {

    @Test(expected = UnsupportedOperationException.class)
    public void testMock() {
        //powermock only run on JVM
        //https://github.com/crittercism/dexmaker/issues/3
        //and in instrumentation test, package local classes can't be mocked by Mockito. Dex's spec?
        DummyModel dummyModel = new DummyModel();
        Mockito.spy(dummyModel);
    }
}
