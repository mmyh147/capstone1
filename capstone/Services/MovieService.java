package com.example.capstone.Services;

import com.example.capstone.Model.Movie;
import com.example.capstone.Model.Product;
import com.example.capstone.Model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class MovieService {


    ArrayList<Movie> movies = new ArrayList<>();
    private final UserService userService;

    public ArrayList<Movie> getAllMovies() {
        return movies;
    }

    public void addMovie(Movie movie) {

        movies.add(movie);

    }

    public boolean updateMovie(String id, Movie updatedMovie){
        for (Movie movie : movies) {
            if(movie.getId().equals(id)){
                movies.set(movies.indexOf(movie), updatedMovie);
                return true;
            }

        }
        return false;


    }

    public boolean deleteMovie(String id){
        for (Movie movie : movies) {
            if(movie.getId().equals(id)){
                movies.remove(movies.indexOf(movie));
                return true;
            }

        }
        return false;
    }


    public boolean watchMovie(String userId, String movieId){
        for (User user : userService.userList){
            if (user.getId().equals(userId)){
                if (user.getIsPrime()){
                    for (Movie movie : movies){
                        if (movie.getId().equals(movieId)){
                            user.addMovieToWatchList(movie);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


}
