package com.example.eamon.jokeviewer;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeViewFragment extends Fragment {

    private JokeViewFragmentListener mCallback;

    public JokeViewFragment() {
    }

    public interface JokeViewFragmentListener {
        String getJokeText();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (JokeViewFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_joke_view, container, false);

        TextView jokeTextView = (TextView) rootView.findViewById(R.id.joke_text_view);
        jokeTextView.setText(mCallback.getJokeText());

        return rootView;
    }
}
