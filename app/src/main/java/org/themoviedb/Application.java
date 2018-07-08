package org.themoviedb;

import android.content.Context;

import org.themoviedb.data.MoviesRepository;
import org.themoviedb.data.local.MoviesLocalRepository;
import org.themoviedb.data.remote.ApiClient;
import org.themoviedb.data.remote.MoviesApi;
import org.themoviedb.data.remote.MoviesNetworkRepository;
import org.themoviedb.di.DependencyProvider;

import timber.log.Timber;

public class Application extends android.app.Application implements DependencyProvider {

    private static DependencyProvider provider;
    private MoviesApi moviesApi;
    private MoviesRepository localRepository;
    private MoviesRepository remoteRepository;

    public static DependencyProvider getDepdencyProvider() {
        return provider;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        provider = this;
        init();
    }

    private void init() {
        this.moviesApi = ApiClient.getInstance().getService();
        this.localRepository = new MoviesLocalRepository();
        this.remoteRepository = new MoviesNetworkRepository(moviesApi());
    }


    @Override
    public Context context() {
        return this;
    }

    @Override
    public MoviesApi moviesApi() {
        return this.moviesApi;
    }

    @Override
    public MoviesRepository getMoviesRepository(boolean isLocal) {
        if (isLocal) return this.localRepository;
        else return this.remoteRepository;
    }
}
