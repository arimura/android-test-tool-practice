package com.hormiga6.androidtesttoolpractice;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.core.IsInstanceOf.any;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by kotaro.arimura on 2016/10/25.
 */
public class HamcrestTest {

    @Test
    public void compareHash() {
        Map<String, String> map1 = new HashMap<String, String>() {{
            put("hoge", "fuga");
            put("aha", "yay");
        }};

        Map<String, String> map2 = new HashMap<String, String>() {{
            put("hoge", "fuga");
            put("aha", "yay");
        }};

        assertThat(map1, equalTo(map2));
        //need hamcrest-library
        assertThat(map1.entrySet(), everyItem(isIn(map2.entrySet())));
    }

    @Test
    public void testNull() {
        String val = null;
        assertThat(val, is(nullValue()));
    }

    @Test
    public void testAny(){
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        assertThat(linkedHashMap,is(any(LinkedHashMap.class)));
        assertThat(linkedHashMap,is(any(HashMap.class)));
        assertThat(linkedHashMap,is(any(Map.class)));
    }

    @Test
    public void testNot(){
        String val = "";
        assertThat(val, is(not(nullValue())));
    }
}
