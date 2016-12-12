package com.hormiga6.androidtesttoolpractice.MemoryLeak;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.hormiga6.androidtesttoolpractice.R;

public class MemoryLeakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ref
        //https://github.com/square/leakcanary
        //https://medium.com/square-corner-blog/leakcanary-detect-all-memory-leaks-875ff8360745#.xpltgbl1p
        //https://medium.com/@nitinkumargove/how-garbage-collection-works-in-dalvik-vm-in-android-bf781ab48531#.jnl3251fe

        // This async task is an anonymous class and therefore has a hidden reference to the outer
        // class MainActivity. If the activity gets destroyed before the task finishes (e.g. rotation),
        // the activity instance will leak.
        new AsyncTask<Void, Void, Void>() {
            @Override protected Void doInBackground(Void... params) {
                // Do some slow work in background
                SystemClock.sleep(10_000);
                return null;
            }
        }.execute();
    }
}
