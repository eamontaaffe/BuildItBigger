package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.ExecutionException;

// You have to use a connected test in order to run an AsyncTask
public class EndpointsAsyncTaskTest extends AndroidTestCase {
    // Test to make sure that the return is non null
    public void testNotNull() {
        JokeProviderEndpointsAsyncTask.EndpointsAsyncTaskCallback cB = new JokeProviderEndpointsAsyncTask.EndpointsAsyncTaskCallback() {
            @Override
            public void onPostExecuteCallback(String result) {
                // Do nothing
            }
        };

        String result;
        try {
            result = new JokeProviderEndpointsAsyncTask().execute(cB).get();
        } catch (ExecutionException|InterruptedException e ) {
            throw new RuntimeException ("JokeProviderEndpointsAsyncTask execution interrupted");
        }

        assertNotNull(result);
    }

    // Too bad you cant test to make sure a joke is funny...
}
