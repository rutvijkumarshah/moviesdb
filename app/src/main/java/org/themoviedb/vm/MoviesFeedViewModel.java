package org.themoviedb.vm;

import android.arch.lifecycle.LiveData;

import org.themoviedb.data.MoviesRepository;
import org.themoviedb.models.MoviesList;

import timber.log.Timber;

public class MoviesFeedViewModel extends BaseViewModel {
    private MoviesRepository localRepo;
    private MoviesRepository remoteRepo;
    private LiveData<MoviesList> movies;

    public MoviesFeedViewModel() {
    }

    public MoviesFeedViewModel(MoviesRepository localRepo, MoviesRepository remoteRepo) {
        this.localRepo = localRepo;
        this.remoteRepo = remoteRepo;
    }

    public void setLocalRepo(MoviesRepository localRepo) {
        this.localRepo = localRepo;
    }

    public void setRemoteRepo(MoviesRepository remoteRepo) {
        this.remoteRepo = remoteRepo;
    }

    @Override
    public void init() {
        if (movies != null) {
            return;
        }
        this.movies = remoteRepo.getNowPlayingMovies();
        Timber.d("Now Movies live data is =" + this.movies);
    }

    public LiveData<MoviesList> getMovies() {
        Timber.d("getMovies live data =" + this.movies);
        return movies;
    }
}
