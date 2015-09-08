package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import au.com.taaffe.builditbigger.backend.myApi.MyApi;

class JokeProviderEndpointsAsyncTask
        extends AsyncTask<JokeProviderEndpointsAsyncTask.EndpointsAsyncTaskCallback, Void, String> {
    private static final String LOG_TAG = JokeProviderEndpointsAsyncTask.class.getSimpleName();
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
    }
}
