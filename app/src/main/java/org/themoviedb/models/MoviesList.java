package org.themoviedb.models;


import org.themoviedb.data.OperationStatus;

import java.util.List;

public class MoviesList {
    private List<Movie> movies;
    private OperationStatus status;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public OperationStatus getStatus() {
        return status;
    }

    public void setStatus(OperationStatus status) {
        this.status = status;
    }
}
