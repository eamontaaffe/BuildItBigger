package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.eamon.jokeviewer.JokeViewActivity;
import com.rey.material.widget.ProgressView;


public class MainActivity extends ActionBarActivity {
    private static String LOG_TAG = MainActivity.class.getSimpleName();
    ProgressView mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgress = (ProgressView) findViewById(R.id.progress);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProgress.setVisibility(View.GONE);
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

    public void tellJoke(View view){
        final Context context = this;
        new JokeProviderEndpointsAsyncTask().execute(new JokeProviderEndpointsAsyncTask.EndpointsAsyncTaskCallback() {
            @Override
            public void onPostExecuteCallback(String result) {
                Log.v(LOG_TAG, "Result is: " + result);
                Log.v(LOG_TAG, "onPostExecuteCallback");
                Intent jokeViewIntent = new Intent(context, JokeViewActivity.class);
                jokeViewIntent.putExtra(JokeViewActivity.EXTRA_JOKE, result);
                context.startActivity(jokeViewIntent);
                    }
                });
        mProgress.setVisibility(View.VISIBLE);
    }
}
