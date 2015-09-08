package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.JokeWizard;
import com.example.eamon.jokeviewer.JokeViewActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends ActionBarActivity {
    private static String LOG_TAG = MainActivity.class.getSimpleName();

    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startJokeAsyncTask();
                requestNewInterstitial();
            }
        });

        requestNewInterstitial();
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
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            startJokeAsyncTask();
        }
    }

    private void startJokeAsyncTask() {
        new JokeProviderEndpointsAsyncTask().execute(new JokeProviderEndpointsAsyncTask.EndpointsAsyncTaskCallback() {
            @Override
            public void onPostExecuteCallback(String result) {
                showJoke(result);
            }
        });
    }

    private void showJoke(String jokeString) {
        Intent jokeViewIntent = new Intent(this, JokeViewActivity.class);
        jokeViewIntent.putExtra(JokeViewActivity.EXTRA_JOKE, jokeString);
        this.startActivity(jokeViewIntent);
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("249EAA8D9F9DB22A0BEAEFC4A360D217")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}
