package org.themoviedb.data.remote;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import org.themoviedb.data.MoviesRepository;
import org.themoviedb.data.OperationStatus;
import org.themoviedb.models.MoviesList;
import org.themoviedb.models.NowPlayingMoviesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesNetworkRepository implements MoviesRepository {

    private MoviesApi api;

    public MoviesNetworkRepository(MoviesApi api) {
        this.api = api;
    }

    @Override
    public LiveData<MoviesList> getNowPlayingMovies() {
        final MutableLiveData<MoviesList> data = new MutableLiveData<>();
        final MoviesList list = new MoviesList();
        list.setStatus(OperationStatus.LOADING);
        data.setValue(list);
        api.getNowPlayingMovies().enqueue(new Callback<NowPlayingMoviesResponse>() {
            @Override
            public void onResponse(Call<NowPlayingMoviesResponse> call, Response<NowPlayingMoviesResponse> response) {
                if (response.isSuccessful()) {
                    NowPlayingMoviesResponse res = response.body();
                    list.setMovies(response.body().getMovies());
                    list.setStatus(OperationStatus.LOADED_SUCCESSFULLY);
                } else {
                    list.setStatus(OperationStatus.FAILED_LOADING);
                }
                data.setValue(list);
            }

            @Override
            public void onFailure(Call<NowPlayingMoviesResponse> call, Throwable t) {
                list.setStatus(OperationStatus.FAILED_LOADING);
                data.setValue(list);
            }
        });
        return data;
    }
}
