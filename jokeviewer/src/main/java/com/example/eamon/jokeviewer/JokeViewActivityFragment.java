package com.example.eamon.jokeviewer;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.JokeWizard;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeViewActivityFragment extends Fragment {

    public JokeViewActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_joke_view, container, false);

        TextView jokeTextView = (TextView) rootView.findViewById(R.id.joke_text_view);
        jokeTextView.setText(JokeWizard.getJoke());

        return rootView;
    }
}
