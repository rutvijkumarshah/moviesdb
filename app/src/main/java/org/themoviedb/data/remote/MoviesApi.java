package org.themoviedb.data.remote;


import org.themoviedb.models.NowPlayingMoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesApi {

    @GET("/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
    Call<NowPlayingMoviesResponse> getNowPlayingMovies();
}
