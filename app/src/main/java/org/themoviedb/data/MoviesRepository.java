package org.themoviedb.data;

import android.arch.lifecycle.LiveData;

import org.themoviedb.models.MoviesList;


public interface MoviesRepository {

    LiveData<MoviesList> getNowPlayingMovies();
}
