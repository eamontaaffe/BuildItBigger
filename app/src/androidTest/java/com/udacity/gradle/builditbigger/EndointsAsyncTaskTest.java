package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.ConcurrentModificationException;
import java.util.concurrent.ExecutionException;

public class EndointsAsyncTaskTest extends AndroidTestCase {
    // Test to make sure that the return is non null
    public void testNotNull() {
        EndpointsAsyncTask.EndpointsAsyncTaskCallback cB = new EndpointsAsyncTask.EndpointsAsyncTaskCallback() {
            @Override
            public void onPostExecuteCallback(String result) {
                // Do nothing
            }
        };

        String result;
        try {
            result = new EndpointsAsyncTask().execute(cB).get();
        } catch (ExecutionException|InterruptedException e ) {
            throw new RuntimeException ("EndpointsAsyncTask execution interrupted");
        }

        assertNotNull(result);
    }

    // Too bad you cant test to make sure a joke is funny...
}
