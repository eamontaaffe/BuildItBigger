package com.example.eamon.jokeviewer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class JokeViewActivity extends AppCompatActivity
        implements JokeViewFragment.JokeViewFragmentListener {

    private String LOG_TAG = JokeViewActivity.class.getSimpleName();

    public static final String EXTRA_JOKE = "extra_joke";

    private String mJokeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_view);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_joke_view, menu);
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



    @Override
    public String getJokeText() {
        // Implemented for the JokeViewFragmentListener to provide a joke to the viewer
        Intent intent = getIntent();
        if (intent != null) {
            mJokeText = intent.getStringExtra(EXTRA_JOKE);
        }

        return mJokeText;
    }
}
