package com.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


public class JokeWizard {
    private static String JokeApiUrl = "http://api.icndb.com/jokes/random";
    private static final String VALUE = "value";
    private static final String JOKE = "joke";

    public static String getJoke() {
        String joke = null;
        try {
            String jsonString = readUrl(JokeApiUrl);
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject valueJson = jsonObject.getJSONObject(VALUE);
            joke = valueJson.getString(JOKE);
        } catch (Exception e) {

        } finally {
            if (joke == null) {
                joke = "Error retrieving joke from API";
            }
            return joke;
        }
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}
