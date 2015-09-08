package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.example.JokeWizard;
import com.example.eamon.jokeviewer.JokeViewActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import javax.security.auth.callback.UnsupportedCallbackException;

import au.com.taaffe.builditbigger.backend.myApi.MyApi;

class EndpointsAsyncTask extends AsyncTask<EndpointsAsyncTask.EndpointsAsyncTaskCallback, Void, String> {
    private static final String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();
    private static MyApi myApiService = null;
    private EndpointsAsyncTaskCallback mCallback;

    public interface EndpointsAsyncTaskCallback {
        void onPostExecuteCallback(String result);
    }

    @Override
    protected String doInBackground(EndpointsAsyncTaskCallback... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                .setRootUrl("https://builditbigger-1061.appspot.com/_ah/api/");
            myApiService = builder.build();
        }

        mCallback = params[0];
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        if (mCallback == null) {
            Log.e(LOG_TAG, "EndpointsAsyncTaskCallback not supplied");
            return null;
        }

        try {
            return myApiService.sayHi().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mCallback.onPostExecuteCallback(result);
//        Intent jokeViewIntent = new Intent(context, JokeViewActivity.class);
//        jokeViewIntent.putExtra(JokeViewActivity.EXTRA_JOKE, JokeWizard.getJoke());
//        context.startActivity(jokeViewIntent);
    }
}
