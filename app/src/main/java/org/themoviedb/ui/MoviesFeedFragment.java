package org.themoviedb.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.themoviedb.Application;
import org.themoviedb.R;
import org.themoviedb.adapter.MoviesAdapter;
import org.themoviedb.data.OperationStatus;
import org.themoviedb.models.Movie;
import org.themoviedb.vm.MoviesFeedViewModel;

import java.util.List;

public class MoviesFeedFragment extends Fragment {

    private MoviesFeedViewModel viewModel;
    private MoviesAdapter adapter;

    public static MoviesFeedFragment newInstance() {
        return new MoviesFeedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movies_feed_fragment, container, false);
        adapter = new MoviesAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.moviesFeed);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }

    private void showLoading(boolean toShow) {
        if (toShow) {
            show("Loading....");
        }
    }

    private void failedLoading() {
        //show error messsage
        showLoading(false);
    }

    private void showMovies(List<Movie> movies) {
        if (movies == null || movies.isEmpty()) {
            handleEmptyList();
        } else {
            adapter.add(movies);
        }
    }

    private void show(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void handleEmptyList() {
        show("what the hack no movies :(");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MoviesFeedViewModel.class);
        viewModel.setRemoteRepo(Application.getDepdencyProvider().getMoviesRepository(false));
        viewModel.init();
        viewModel.getMovies().observe(this, moviesList -> {
            if (moviesList.getMovies() == null) {
                if (moviesList.getStatus() == OperationStatus.LOADING) {
                    showLoading(true);
                } else if (moviesList.getStatus() == OperationStatus.FAILED_LOADING) {
                    failedLoading();
                } else {
                    showMovies(moviesList.getMovies());
                }
            } else {
                showMovies(moviesList.getMovies());
            }
        });


    }

}
