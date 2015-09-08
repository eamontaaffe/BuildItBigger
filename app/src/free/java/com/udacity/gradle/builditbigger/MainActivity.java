package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.eamon.jokeviewer.JokeViewActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends ActionBarActivity {
    private static String LOG_TAG = MainActivity.class.getSimpleName();

    private InterstitialAd mInterstitialAd;
    private boolean isInterstitialAdOpened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdOpened() {
                isInterstitialAdOpened = true;
            }

            @Override
            public void onAdClosed() {
                isInterstitialAdOpened = false;
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
        }
        startJokeAsyncTask();
    }

    private void startJokeAsyncTask() {
        new JokeProviderEndpointsAsyncTask().execute(new JokeProviderEndpointsAsyncTask.EndpointsAsyncTaskCallback() {
            @Override
            public void onPostExecuteCallback(String result) {
                showJoke(result);
            }
        });
    }

    private void showJoke(final String jokeString) {
        if(isInterstitialAdOpened == true) {
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdOpened() {
                    isInterstitialAdOpened = true;
                }

                @Override
                public void onAdClosed() {
                    isInterstitialAdOpened = false;
                    openJokeViewer(jokeString);
                    requestNewInterstitial();
                }
            });
        } else {
            openJokeViewer(jokeString);
        }
    }

    private void openJokeViewer(String jokeString) {
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdOpened() {
                isInterstitialAdOpened = true;
            }

            @Override
            public void onAdClosed() {
                isInterstitialAdOpened = false;
                requestNewInterstitial();
            }
        });

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
