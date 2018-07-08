package org.themoviedb.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.themoviedb.R;


public class MainActivity extends AppCompatActivity {

    private static String TAG_MOVIES_FEED_FRAG = "TAG_MOVIES_FEED_FRAG";
    MoviesFeedFragment feedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_feed);
        feedFragment = (MoviesFeedFragment) getSupportFragmentManager().findFragmentByTag(TAG_MOVIES_FEED_FRAG);
        if (feedFragment == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.moviesContainer, new MoviesFeedFragment(), TAG_MOVIES_FEED_FRAG);
            ft.commit();
        }

    }
}
