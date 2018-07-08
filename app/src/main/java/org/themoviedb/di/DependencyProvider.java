package org.themoviedb.di;

import android.content.Context;

import org.themoviedb.data.MoviesRepository;
import org.themoviedb.data.remote.MoviesApi;

/**
 * Poor man's Dagger for this small project
 */
public interface DependencyProvider {
    Context context();

    MoviesApi moviesApi();

    MoviesRepository getMoviesRepository(boolean isLocal);
}
