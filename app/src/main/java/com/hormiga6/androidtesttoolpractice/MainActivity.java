package com.hormiga6.androidtesttoolpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hormiga6.androidtesttoolpractice.MemoryLeak.MemoryLeakActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static Map<Integer, Class> activityMap = new HashMap<Integer, Class>(){{
        put(R.id.buttonList, ListActivity.class);
        put(R.id.buttonMemoryLeak, MemoryLeakActivity.class);
    }};

    private static Map<Integer, Bundle> bundleMap = new HashMap<Integer, Bundle>(){{
        Bundle listBundle = new Bundle();
        listBundle.putString("hoge","fuga");
        put(R.id.buttonList, listBundle);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void click(View view){
        Class clazz = activityMap.get(view.getId());
        Intent intent = new Intent(this, clazz);
        Bundle bundle = bundleMap.get(view.getId());
        if(bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}
