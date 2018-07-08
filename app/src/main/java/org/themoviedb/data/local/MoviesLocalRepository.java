package org.themoviedb.data.local;


import android.arch.lifecycle.LiveData;

import org.themoviedb.data.MoviesRepository;
import org.themoviedb.models.MoviesList;

public class MoviesLocalRepository implements MoviesRepository {

    @Override
    public LiveData<MoviesList> getNowPlayingMovies() {
        return null;
    }
}
