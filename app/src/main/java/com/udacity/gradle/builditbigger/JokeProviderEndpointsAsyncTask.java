package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

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
            // Uncomment this section to use the cloud deployed endpoints server
            /*
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport()
                    , new AndroidJsonFactory(), null)
                .setRootUrl("https://builditbigger-1061.appspot.com/_ah/api/");
            */

            // Uncomment this section to use the locally deployed endpoints server
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/") // 10.0.2.2 is localhost's IP address in Android emulator
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        mCallback = params[0];

        if (mCallback == null) {
            Log.e(LOG_TAG, "EndpointsAsyncTaskCallback not supplied");
            return null;
        }

        try {
            Log.v(LOG_TAG,"getData()" + myApiService.getJoke().execute().getData());
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mCallback.onPostExecuteCallback(result);
    }
}
