package org.themoviedb.adapter;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.themoviedb.databinding.MovieItemBinding;
import org.themoviedb.models.Movie;

import java.util.List;

public class MoviesAdapter extends ListAdapter<Movie, MoviesAdapter.ViewHolder> {

    private static final DiffUtil.ItemCallback<Movie> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Movie>() {
                @Override
                public boolean areItemsTheSame(Movie oldItem, Movie newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(Movie oldItem, Movie newItem) {
                    return (oldItem.equals(newItem));
                }
            };

    public MoviesAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MovieItemBinding movieItemBinding = MovieItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(movieItemBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie m = getItem(position);
        holder.bind(m);
    }

    public void add(List<Movie> newMovies) {
        submitList(newMovies); // DiffUtil takes care of the check
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private MovieItemBinding binding;

        ViewHolder(MovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Movie movie) {
            binding.setMovie(movie);
            binding.executePendingBindings();
        }
    }
}
