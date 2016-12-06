package com.hormiga6.androidtesttoolpractice;

import org.junit.Test;

import java.util.ArrayList;

public class ConccurentTest {
    @Test
    public void testSimpleRunnable() throws InterruptedException {
        ArrayList<SimpleRunnable> simpleRunnables = new ArrayList<SimpleRunnable>() {{
            add(new SimpleRunnable("hoge"));
            add(new SimpleRunnable("fuga"));
        }};
        TestUtility.assertConccurent("bow",simpleRunnables,100);
    }

    static class SimpleRunnable implements Runnable{
        private final String name;
        SimpleRunnable(String name){
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println("Hi, " + name +" in thread:" + Thread.currentThread().getName());
        }
    }
}
